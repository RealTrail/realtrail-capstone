"use strict";

$(document).ready(() => {
    // get all the trail names
    let trails = $("#trails .name").map((index, element) => element.value).get();
    console.log(trails);

    let longitudeArr = [], latitudeArr = [];

    $(".mapPoints > input").each((index) => {
        console.log(index, $(this).attr("name"), $(this).val());
        // let trailId = $(this).attr("name");
        // console.log(trailId);
        // if (index % 2 === 1) {
        //     console.log($(this).val());
        //     longitudeArr.push({id: trailId, longitude: $(this).val()});
        // } else {
        //     latitudeArr.push({id: trailId, latitude: $(this).val()});
        // }
    });

    console.log(longitudeArr);

    // $("form .trailOption").on('change', () => {
    //
    //     if ($("input[name='trailOption']:checked").val() === "existing trail") { // user chooses to pick an existing trail
    //         $("div.trailOptions").show();
    //         $("div.trailOptions").on('change', () => {
    //
    //
    //             let selectedTrailId = $("#trailOptions").find(":selected").val();
    //             console.log(selectedTrailId);
    //             let location = trails[selectedTrailId - 1];
    //             console.log(location);
    //
    //             // get the selected trail coordinates
    //             let coordinates = getSelectedTrailCoordinates(selectedTrailId, longitudeArr, latitudeArr);
    //
    //             // show the map around the location
    //             geocode(location, mapboxToken).then((results) => {
    //                 console.log(results);
    //                 let map = showMap(results);
    //                 map.on('load', () => {
    //                     map.addSource('route', {
    //                         'type': 'geojson',
    //                         'data': {
    //                             'type': 'Feature',
    //                             'properties': {},
    //                             'geometry': {
    //                                 'type': 'LineString',
    //                                 'coordinates': coordinates
    //                             }
    //                         }
    //                     });
    //                     map.addLayer({
    //                         'id': 'route',
    //                         'type': 'line',
    //                         'source': 'route',
    //                         'layout': {
    //                             'line-join': 'round',
    //                             'line-cap': 'round'
    //                         },
    //                         'paint': {
    //                             'line-color': '#c0273d',
    //                             'line-width': 6
    //                         }
    //                     });
    //                 });
    //             });
    //         });
    //     } else { // user chooses to customize a trail
    //
    //         showMap();
    //
    //         $("#mapSearch").click(() => {
    //             // get coordinates using geocode
    //             geocode($("#searchedName").val(), mapboxToken).then((results) => {
    //                 console.log(results);
    //                 // fly to the place searched
    //                 map.flyTo({
    //                     center: results,
    //                     zoom: 12
    //                 });
    //
    //                 // Create an empty GeoJSON feature collection, which will be used as the data source for the route before users add any new data
    //                 var nothing = turf.featureCollection([]);
    //             })
    //         });
    //     }
    // });
});

mapboxgl.accessToken = mapboxToken;
$("div.trailOptions").hide();
$("#map").hide();

function showMap(coordinates) {
    $("#map").show();
    return new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/outdoors-v11',
        center: coordinates,
        zoom: 15
    });
}

function getSelectedTrailCoordinates(trailId, longitudeArr, latitudeArr) {
    let coordinates = [];
    for (let i = 0; i < longitudeArr.length; i++) {
        if (longitudeArr[i].id === trailId) {
            coordinates.push([longitudeArr[i].longitude, latitudeArr[i].latitude]);
        }
    }
    return coordinates;
}


