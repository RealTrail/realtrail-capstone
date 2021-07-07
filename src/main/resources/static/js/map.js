"use strict";

$(document).ready(() => {

    $("#trailOption1").on("click", () => { // user chooses to pick an existing trail
        $("#trailOptions").show();
        $("#trailOptions").on('change', () => {

            let selectedTrailId = $("#trailOptions").find(":selected").val();
            console.log(selectedTrailId);

            // check if selectedTrailId is empty or null
            if (selectedTrailId !== null && selectedTrailId.length !== 0) {
                let coordinates = [], trailPoint = [];
                $.ajax({
                    type: "GET",
                    url: "/map/" + selectedTrailId,
                    dataType: 'json',
                    success: (response) => {
                        console.log(response);
                        // get the trail location
                        trailPoint = [response[0].trail.longitude, response[0].trail.latitude];

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
        $(".mask2").addClass("active2");
        showDefaultMap();

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

            // if (trail.name && trail.length && trail.difficultyLevel && trail.type && trail.trailDetails) {
            //     $.ajax({
            //         url: "/trails/create",
            //         type: "POST",
            //         data: JSON.stringify(trail),
            //         contentType: "application/json",
            //         dataType: "json",
            //         timeout: 600000,
            //         success: (response) => {
            //             console.log("trail saved!");
            //             console.log(response);
            //
            //         },
            //         error: (error) => {
            //             console.log("Error: ", error);
            //         }
            //     });
            // }
        });

        $("#mapSearch").click(() => {
            // get coordinates using geocode
            geocode($("#searchedName").val(), mapboxToken).then((results) => {
                console.log(results);
                // fly to the place searched
                map.flyTo({
                    center: results,
                    zoom: 12
                });

                // Create an empty GeoJSON feature collection, which will be used as the data source for the route before users add any new data
                var nothing = turf.featureCollection([]);
            })
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
