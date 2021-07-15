"use strict";

$(document).ready(() => {
    let trail = {};
    let existingTrails = $("#trailOptions option").map((index, element) => element.text);
    console.log(existingTrails);

    // user chooses to pick an existing trail
    $("#trailOption1").on("click", () => {
        $("#trailLocation").addClass("hidden");
        $("#trailOptions").removeClass("hidden");
        $("#trailOptions").on('change', () => {
            // get the trail id and location
            let selectedTrailId = $("select#trailOptions").find(":selected").val();
            let trailPoint = $("select#trailOptions").find(":selected").attr("name").split(" ");
            console.log(selectedTrailId);
            console.log(trailPoint);

            // check if selectedTrailId is empty or null
            if (selectedTrailId !== null && selectedTrailId.length !== 0) {
                let coordinates = [];
                $.ajax({
                    type: "GET",
                    url: "/trails/" + selectedTrailId + "/map",
                    dataType: 'json',
                    success: (response) => {
                        // loop through the array of coordinate object to get an array of coordinates
                        for (let coordinateObj of response) {
                            let mapPoint = [coordinateObj.longitude, coordinateObj.latitude];
                            coordinates.push(mapPoint);
                        }
                        console.log(coordinates);

                        // refresh the map and center to the trail point
                        map = showMap(trailPoint);

                        if (selectedTrailId <= 21) {
                            // show the trail route
                            map.on('load', () => {
                                map.addSource('route', {
                                    'type': 'geojson',
                                    'data': {
                                        'type': 'Feature',
                                        'properties': {},
                                        'geometry': {
                                            'type': 'LineString',
                                            'coordinates': coordinates
                                        }
                                    }
                                });
                                map.addLayer({
                                    'id': 'route',
                                    'type': 'line',
                                    'source': 'route',
                                    'layout': {
                                        'line-join': 'round',
                                        'line-cap': 'round'
                                    },
                                    'paint': {
                                        'line-color': '#048d3b',
                                        'line-width': 4
                                    }
                                });
                            });
                        } else {
                            map.on('load', () =>{
                                let trailToSearch = coordinates.join(';');
                                getMatch(trailToSearch);
                            });
                        }
                    },
                    error: (error) => {
                        console.log("Error connecting the server");
                        console.log(error);
                        window.location = "/error";
                    }
                });
            }
        });
    });

    // user chooses to customize trail
    $("#trailOption2").on("click", () => {
        // set map center back to San Antonio
        map = showMap([-98.491142, 29.424349]);
        map.on('load', () => {
            removeRoute();
        });

        $("#trailOptions").addClass("hidden");
        $("#trailLocation").removeClass("hidden");
        $(".mask2").addClass("active2");
    });


    // click upload images to upload images
    $("#images").click(() => uploadImages());

    // click create trail to create a new trail
    $("#createTrail").click((e) => {
        e.preventDefault();

        // check if the user has entered info in the inputs
        if ($("#trailName").val() === "") {
            $("p.trailName").text("Name cannot be empty!");
            trail = {};
        } else {
            $("p.trailName").css("display: none");
        }
        if ($("#trailLength").val() === "") {
            $("p.trailLength").text("Length cannot be empty!");
            trail = {};
        } else {
            $("p.trailLength").css("display: none");
        }
        if ($("input[name='difficultyLevel']:checked").val() === undefined) {
            $("p.difficultyLevel").text("Difficulty level cannot be unchecked!");
            trail = {};
        } else {
            $("p.difficultyLevel").css("display: none");
        }
        if ($("input[name='trailType']:checked").val() === undefined) {
            $("p.trailType").text("Type cannot be unchecked!");
            trail = {};
        } else {
            $("p.trailType").css("display: none");
        }
        if ($("#trailDetails").val() === "") {
            $("p.trailDetails").text("Trail details cannot be empty!");
            trail = {};
        } else {
            $("p.trailDetails").css("display: none");
        }

        // get the trail info typed in create trail modal form
        trail.name = formatTrailName($("#trailName").val());
        trail.length = parseFloat($("#trailLength").val());
        trail.difficultyLevel = $("input[name='difficultyLevel']:checked").val();
        trail.type = $("input[name='trailType']:checked").val();
        trail.trailDetails = $("#trailDetails").val();

        // check if trail images uploaded
        if ($("#hidden").val() !== undefined && $("#hidden").val() !== "") {
            // get uploaded trail images
            let images = $("#hidden").val().split(" ");
            console.log(images);
            trail.trailImages = images;
        } else {  // set default trail image
            trail.trailImages = ['https://cdn.filestackcontent.com/Axfq5ASWmn6RlEO7Sh1w'];
        }

        // check if the trail already exist in the database
        if (isExist(trail.name, existingTrails)) {
            trail = {};
            $("p.trailExist").text("Trail already exists!")
        } else {
            $("p.trailExist").css("display: none");
        }

        console.log(trail);

        // check if the trail already exist in the database
        if (isExist(trail.name, existingTrails)) {
            $("p.trailExist").text("Trail already exists!");
            trail = {};
        } else {
            $("p.trailExist").css("display: none");
        }

        if (trail.name && trail.length && trail.difficultyLevel && trail.type && trail.trailDetails && (!isExist(trail.name, existingTrails))) {

            // do ajax post to save the trail in db
            $.ajax({
                url: "/trails/create",
                type: "POST",
                data: JSON.stringify(trail),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                timeout: 600000,
                success: (response) => {
                    console.log("trail saved!");
                    console.log(response);
                    $("#trailId").val(response.id);

                    console.log(existingTrails);

                    closeModalTwo();

                    // Add the Draw control to your map
                    map.addControl(draw);

                    // add create, update, or delete actions
                    map.on('draw.create', updateRoute);
                    map.on('draw.update', updateRoute);
                    map.on('draw.delete', updateRoute);

                    map.on('click', (e) => {
                        console.log(e.lngLat);
                        $("#trailPoint").val(e.lngLat.lng + "," + e.lngLat.lat);
                        console.log($("#trailPoint").val());
                    });
                },
                error: (error) => {
                    console.log("Error: ", error);
                    window.location = "/error";
                }
            });
        }
    });
});

// Function for close the Modal
function closeModalTwo(){
    $(".mask2").removeClass("active2");
}

// Call the closeModal function on the clicks/keyboard
$(".close, .mask2").on("click", function(){
    closeModalTwo();
});

$(document).keyup((e) => {
    if (e.keyCode === 27) {
        closeModalTwo();
    }
});


mapboxgl.accessToken = mapboxToken;
let map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/outdoors-v11',
    center: [-98.491142, 29.424349],
    zoom: 17
});
let mapEl = document.getElementById('map');

// Change Dimensions
mapEl.style.width = '100vw';
mapEl.style.height = '100vh';

// Fix the map display
map.resize();

// initialize draw
let draw = new MapboxDraw({
    displayControlsDefault: false,
    controls: {
        line_string: true,
        trash: true
    },
    styles: drawStyles()
});

// type in the search area to center the map to the searched location
$("#mapSearch").click(() => {
    console.log($("#searchedName").val());
    // get coordinates using geocode
    geocode($("#searchedName").val(), mapboxToken).then((results) => {
        console.log(results);
        // fly to the place searched
        map.flyTo({
            center: results,
            zoom: 13,
            minZoom: 11  // keep it local
        });
    });
});

function showMap(trailPoint) {
    return new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/outdoors-v11',
        center: trailPoint,
        zoom: 17,
        minZoom: 11
    });
}
map.addControl(new mapboxgl.FullscreenControl());

// set up drawOptions
function drawStyles() {
    return [
        // ACTIVE (being drawn)
        // line stroke
        {
            "id": "gl-draw-line",
            "type": "line",
            "filter": ["all", ["==", "$type", "LineString"], ["!=", "mode", "static"]],
            "layout": {
                "line-cap": "round",
                "line-join": "round"
            },
            "paint": {
                "line-color": "#3b9ddd",
                "line-dasharray": [0.2, 2],
                "line-width": 4,
                "line-opacity": 0.7
            }
        },
        // vertex point halos
        {
            "id": "gl-draw-polygon-and-line-vertex-halo-active",
            "type": "circle",
            "filter": ["all", ["==", "meta", "vertex"], ["==", "$type", "Point"], ["!=", "mode", "static"]],
            "paint": {
                "circle-radius": 10,
                "circle-color": "#FFF"
            }
        },
        // vertex points
        {
            "id": "gl-draw-polygon-and-line-vertex-active",
            "type": "circle",
            "filter": ["all", ["==", "meta", "vertex"], ["==", "$type", "Point"], ["!=", "mode", "static"]],
            "paint": {
                "circle-radius": 6,
                "circle-color": "#3b9ddd",
            }
        },
    ]
}

// get the coordinates that were just drew to make your directions request
function updateRoute() {
    removeRoute();  // overwrite any existing layers
    let data = draw.getAll();
    let lastFeature = data.features.length - 1;
    let coords = data.features[lastFeature].geometry.coordinates;
    let newCoords = coords.join(';');
    $("#createdCoordinates").val(newCoords);
    console.log(coords);
    console.log($("#createdCoordinates").val());
    getMatch(newCoords);
}

// make a directions request
function getMatch(e) {
    let url = 'https://api.mapbox.com/directions/v5/mapbox/walking/' + e +'?geometries=geojson&steps=true&&access_token=' + mapboxToken;
    let request = new XMLHttpRequest();
    request.responseType = 'json';
    request.open('GET', url, true);
    request.onload  = () => {
        let jsonResponse = request.response;
        console.log(jsonResponse);
        let route = jsonResponse.routes[0].geometry.coordinates;
        // add the route to the map
        addRoute(route);
    };
    request.send();
}

// adds the route as a layer on the map
function addRoute (coordinates) {
    // check if the route is already loaded
    if (map.getSource('route')) {
        map.removeLayer('route')
        map.removeSource('route')
    } else {
        map.addLayer({
            "id": "route",
            "type": "line",
            "source": {
                "type": "geojson",
                "data": {
                    "type": "Feature",
                    "properties": {},
                    "geometry": {
                        type: 'LineString',
                        coordinates: coordinates
                    }
                }
            },
            "layout": {
                "line-join": "round",
                "line-cap": "round"
            },
            "paint": {
                "line-color": "#048d3b",
                "line-width": 4,
                "line-opacity": 0.8
            }
        });
    }
}

// remove the layer if it exists
function removeRoute () {
    if (map.getSource('route')) {
        map.removeLayer('route');
        map.removeSource('route');
    } else {
        return;
    }
}

// get trail address
function getTrailAddress() {
    if ($("#line2").val() === undefined || $("#line2").val() === "") {
        return $("#line1").val() + ", " + $("line2").val() + ", " + $("#city").val() + ", " + $("#state").val() + " " + $("#zipCode").val();
    } else {
        return $("#line1").val() + ", " + $("#city").val() + ", " + $("#state").val() + ", " + $("#zipCode").val();
    }
}

// format trail name
function formatTrailName(trailName) {
    let nameArr = trailName.trim().split(" ");
    for (let name of nameArr) {
        name = formatString(name);
    }
    return nameArr.join(" ");
}

function formatString(string) {
    return string.slice(0, 1).toUpperCase() + string.slice(1).toLowerCase();
}

function isExist(string, array) {
    for (let i = 0; i < array.length; i++) {
        if (string === array[i]) {
            return true;
        }
    }
}
