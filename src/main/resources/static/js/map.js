"use strict";

$(document).ready(() => {
    console.log($(".name").val());
    // get all the trail names
    let trails = $("#trails .name").map((index, element) => element.value).get();
    let trailObjArr = [];

    console.log(trails);

    for (let i = 0; i < trails.length; i++) {
        let obj = {id: i + 1, name: trails[i]};
        trailObjArr.push(obj);
    }

    let longitudeArr = [], latitudeArr = [];

    $(".mapPoints > input.lng").each((index) => {
        let trailId = $(this).attr("name");
        if (index % 2 === 1) {
            longitudeArr.push({id: trailId, longitude: $(this).val()});
        } else {
            latitudeArr.push({id: trailId, latitude: $(this).val()});
        }
    });
    let longitudes = $(".mapPoints > input.lng").map((index, element) => element.value).get();
    let latitudes = $(".mapPoints > input.lat").map((index, element) => element.value).get();


    console.log(longitudes);
    console.log(longitudeArr);

    for (let i = 1; i < trails.length; i++) {

    }

    $("form .trailOption").on('change', () => {

        if ($("input[name='trailOption']:checked").val() === "existing trail") { // user chooses to pick an existing trail
            $("div.trailOptions").show();
            $("div.trailOptions").on('change', () => {


                let selectedTrailId = $("#trailOptions").find(":selected").val();
                console.log(selectedTrailId);

                // get the selected trail coordinates
                let coordinates = getSelectedTrailCoordinates(selectedTrailId, longitudeArr, latitudeArr);

                // show the map around the location
                geocode(location, mapboxToken).then((results) => {
                    console.log(results);
                    let map = showMap(results);
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
        } else { // user chooses to customize a trail

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
        }
    });
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


