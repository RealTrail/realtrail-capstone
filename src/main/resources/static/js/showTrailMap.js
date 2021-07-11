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

                $("#map").css("height",  "700px");
                // show the map around the location
                let map = new mapboxgl.Map({
                    container: 'map',
                    style: 'mapbox://styles/mapbox/outdoors-v11',
                    center: trailPoint,
                    zoom: 15
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
                                'line-color': '#dd5765',
                                'line-width': 4
                            }
                        });
                    });
                } else {
                    map.on('load', () =>{
                        let trailToSearch = coordinates.join(';');
                        console.log(trailToSearch);
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