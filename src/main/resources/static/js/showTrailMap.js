"use strict";

$(document).ready(() => {

    let id = $("#trailId").val();

    // get the trail location coordinates
    let trailPoint = $("#trailLocation").val().split(",");
    console.log(trailPoint);

    let coordinates = [];

    $("#getMap").click(() =>{
        $.ajax({
            type: "GET",
            url: "/trails/" + id + "/map",
            dataType: 'json',
            success: (response) => {
                // loop through the array of coordinate object to get an array of coordinates
                for (let coordinateObj of response) {
                    let mapPoint = [coordinateObj.longitude, coordinateObj.latitude];
                    coordinates.push(mapPoint);
                }
                console.log(coordinates);

                // show the map around the location
                map = showMap(trailPoint);

                if (id <= 21) {
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
                                'line-color': '#dd5765',
                                'line-width': 4
                            }
                        });
                    });
                } else {
                    map.on('load', () =>{
                        let trailToSearch = coordinates.join(';');
                        addRoute(trailToSearch);
                    });
                }
            },
            error: (error) => {
                console.log("Error connecting the server");
                console.log(error);
                // window.location = "/error";
            }
        });
    });
});

mapboxgl.accessToken = mapboxToken;