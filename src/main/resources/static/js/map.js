"use strict";

$(document).ready(() => {

    // user chooses to pick an existing trail
    $("#trailOption1").on("click", () => {
        $("#trailOptions").show();
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
                        console.log(response);

                        // loop through the array of coordinate object to get an array of coordinates
                        for (let coordinateObj of response) {
                            let mapPoint = [coordinateObj.longitude, coordinateObj.latitude];
                            coordinates.push(mapPoint);
                        }

                        // show the map around the location
                        let map = showMap(trailPoint);
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
                                    'line-color': '#c0273d',
                                    'line-width': 5
                                }
                            });
                        });
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
        $("#trailOptions").hide();
        $(".mask2").addClass("active2");

        // click upload images to upload images
        $("#images").click(() => {
            uploadImages();
        });

        $("#createTrail").click((e) => {
            e.preventDefault();

            // check if the user has entered info in the inputs
            if ($("#trailName").val() === undefined) {
                $("p.trailName").text("Name cannot be empty!");
            } else {
                $("p.trailName").css("display: none");
            }
            if ($("#trailLength").val() === undefined) {
                $("p.trailLength").text("Length cannot be empty!");
            } else {
                $("p.trailLength").css("display: none");
            }
            if ($("input[name='difficultyLevel']:checked").val() === undefined) {
                $("p.difficultyLevel").text("Difficulty level cannot be unchecked!");
            } else {
                $("p.difficultyLevel").css("display: none");
            }
            if ($("input[name='trailType']:checked").val() === undefined) {
                $("p.trailType").text("Type cannot be unchecked!");
            } else {
                $("p.trailType").css("display: none");
            }
            if ($("#trailDetails").val() === undefined) {
                $("p.trailDetails").text("Trail details cannot be empty!");
            } else {
                $("p.trailDetails").css("display: none");
            }

            let trail = {};
            // get the trail info typed in create trail modal form
            trail.name = $("#trailName").val();
            trail.length = parseFloat($("#trailLength").val());
            trail.difficultyLevel = $("input[name='difficultyLevel']:checked").val();
            trail.type = $("input[name='trailType']:checked").val();
            trail.trailDetails = $("#trailDetails").val();

            console.log(trail);
            console.log($("#hidden").val());

            if ($("#hidden").val() !== undefined && $("#hidden").val() !== "") {
                let images = $("#hidden").val().substring(0, $("#hidden").val().length - 1).split(", ");
                console.log(images);
                trail.trailImages = images;
            }

            console.log(trail);

            if (trail.name && trail.length && trail.difficultyLevel && trail.type && trail.trailDetails) {
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
                        closeModalTwo();

                        console.log($("#trailId").val());

                        showDefaultMap();
                        $("#mapSearch").click(() => {
                            // get coordinates using geocode
                            geocode($("#searchedName").val(), mapboxToken).then((results) => {
                                console.log(results);
                                // fly to the place searched
                                map.flyTo({
                                    center: results,
                                    zoom: 13,
                                    minZoom: 11  // keep it local
                                });

                                let draw = new MapboxDraw({
                                    displayControlsDefault: false,
                                    controls: {
                                        line_string: true,
                                        trash: true
                                    },
                                    styles: [
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
                                });

                                // add create, update, or delete actions
                                map.on('draw.create', updateRoute);
                                map.on('draw.update', updateRoute);
                                map.on('draw.delete', removeRoute);


                            })
                        });
                    },
                    error: (error) => {
                        console.log("Error: ", error);
                        // window.location = "/error";
                    }
                });
            }
        });
    });
});

mapboxgl.accessToken = mapboxToken;
$("#trailOptions").hide();
$("#trailLocation").hide();
$("#map").hide();

function showMap(trailPoint) {
    $("#map").show();
    return new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/outdoors-v11',
        center: trailPoint,
        zoom: 15
    });
}

function showDefaultMap() {
    $("#trailLocation").show();
    $("#map").show();
    return new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/outdoors-v11',
        center: [-98.491142, 29.424349],
        zoom: 15
    });
}

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


// use the coordinates you just drew to make your directions request
function updateRoute() {
    removeRoute(); // overwrite any existing layers
    let data = draw.getAll();
    let answer = document.getElementById('calculated-line');
    let lastFeature = data.features.length - 1;
    let coords = data.features[lastFeature].geometry.coordinates;
    let newCoords = coords.join(';')
    getMatch(newCoords);
}

// make a directions request
function getMatch(e) {
    // https://www.mapbox.com/api-documentation/#directions
    let url = 'https://api.mapbox.com/directions/v5/mapbox/cycling/' + e +'?geometries=geojson&steps=true&&access_token=' + mapboxToken;
    let request = new XMLHttpRequest();
    request.responseType = 'json';
    request.open('GET', url, true);
    request.onload  = () => {
        let jsonResponse = request.response;
        console.log(jsonResponse);
        let distance = jsonResponse.routes[0].distance*0.001; // convert to km
        let duration = jsonResponse.routes[0].duration/60; // convert to minutes
        // add results to info box
        document.getElementById('calculated-line').innerHTML = 'Distance: ' + distance.toFixed(2) + ' km<br>Duration: ' + duration.toFixed(2) + ' minutes';
        let coords = jsonResponse.routes[0].geometry;
        // add the route to the map
        addRoute(coords);
    };
    request.send();
}

// adds the route as a layer on the map
function addRoute (coords) {
    // check if the route is already loaded
    if (map.getSource('route')) {
        map.removeLayer('route')
        map.removeSource('route')
    } else{
        map.addLayer({
            "id": "route",
            "type": "line",
            "source": {
                "type": "geojson",
                "data": {
                    "type": "Feature",
                    "properties": {},
                    "geometry": coords
                }
            },
            "layout": {
                "line-join": "round",
                "line-cap": "round"
            },
            "paint": {
                "line-color": "#3b9ddd",
                "line-width": 8,
                "line-opacity": 0.8
            }
        });
    };
}

// remove the layer if it exists
function removeRoute () {
    if (map.getSource('route')) {
        map.removeLayer('route');
        map.removeSource('route');
        document.getElementById('calculated-line').innerHTML = '';
    } else  {
        return;
    }
}