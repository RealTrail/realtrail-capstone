"use strict";

mapboxgl.accessToken = mapboxToken;
const map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/outdoors-v11', // style URL
    center: [-98.491142, 29.424349], // starting position [lng, lat]
    zoom: 9 // starting zoom
});

