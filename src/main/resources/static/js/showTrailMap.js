"use strict";

$(document).ready(() => {
    let id = $("#trailId").val();
    // get the trail location coordinates
    let trailPoint = $("#trailLocation").val().split(",");
    console.log(trailPoint);

    let coordinates = [];

    $("#getMap").click(() =>{
        // get the coordinates from db
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

                $("#map").addClass("map");
                // show the map around the location
                let map = new mapboxgl.Map({
                    container: 'map',
                    style: 'mapbox://styles/mapbox/outdoors-v11',
                    center: trailPoint,
                    zoom: 15,
                    minZoom: 11
                });

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
                                'line-color': '#048d3b',
                                'line-width': 4
                            }
                        });
                    });
                } else {
                    map.on('load', () =>{
                        let trailToSearch = coordinates.join(';');
                        console.log(trailToSearch);
                        let url = 'https://api.mapbox.com/directions/v5/mapbox/walking/' + trailToSearch +'?geometries=geojson&steps=true&&access_token=' + mapboxToken;
                        let request = new XMLHttpRequest();
                        request.responseType = 'json';
                        request.open('GET', url, true);
                        request.onload  = () => {
                            let jsonResponse = request.response;
                            console.log(jsonResponse);
                            let route = jsonResponse.routes[0].geometry.coordinates;
                            // add the route to the map
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
                                            coordinates: route
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
                        };
                        request.send();
                    });
                }
            },
            error: (error) => {
                console.log("Error connecting the server");
                console.log(error);
                window.location = "/error";
            }
        });
    });

    $(".star").hover(
        () => {
            let rating = parseInt($(this).attr("data-value"), 10);
            console.log(rating);
            $(this).parent().children().each((element) => {
                if (element < rating) {
                    $(this).addClass("added");
                } else {
                    $(this).removeClass("added");
                }
            });
        },
        () => {
            $(this).parent().children().each((element) => $(this).removeClass("added"));
        });

    $("#stars .star").click(() => {
        let rating = Number($(this).attr("data-value"));
        console.log(rating);
        for (let i = 0; i < 5; i++) {
            $(this).parent().children().eq(i).removeClass('added');
        }

        for (let i = 0; i < rating; i++) {
            $(this).parent().children().eq(i).addClass('added');
        }
        let ratingValue = parseInt($("#stars > .star.added").last().data("value"));
        $("#rating").val(ratingValue);
    });
});

mapboxgl.accessToken = mapboxToken;

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