"use strict";

$(document).ready(() => {

    $("#trailOption1").on("click", () => { // user chooses to pick an existing trail
        $("#trailOptions").show();
        $("#trailOptions").on('change', () => {

            let selectedTrailId = $("#trailOptions").find(":selected").val();
            console.log(selectedTrailId);

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
                },
                error: (error) => {
                    console.log("Error connecting the server");
                    console.log(error);
                    window.location = "/Error";
                }
            });

            console.log(coordinates);
            console.log(trailPoint);

            // show the map around the location
            let map = showMap(trailPoint);
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
                        'line-width': 6
                    }
                });
            });
        });
    });

    // user chooses to customize trail
    $("#trailOption2").on("click", () => {
        $(".mask2").addClass("active2");
        showMap();

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