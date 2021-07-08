"use strict";

$(document).ready(() => {
    mapboxgl.accessToken = mapboxToken;

    let coordinates = $("#coordinates").val().split(" ");
    console.log(coordinates);

    // get all the mapPoints on the trail
    let mapPoints = [];
    $(".mapPoints").each(() => {
        let mapPoint = $(this).val().split(" ");
        console.log(mapPoint);
        mapPoints.push(mapPoint);
    });

    let map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/outdoors-v11',
        center: coordinates,
        zoom: 15
    });
    map.on('load', () => {
        map.addSource('route', {
            'type': 'geojson',
            'data': {
                'type': 'Feature',
                'properties': {},
                'geometry': {
                    'type': 'LineString',
                    'coordinates': mapPoints
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

});