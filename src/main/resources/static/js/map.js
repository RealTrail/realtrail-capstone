"use strict";

$(document).ready(() => {

    $("form .trailOption").on('change', () => {

        if ($("input[name='trailOption']:checked").val() === "existing trail") { // user chooses to pick an existing trail
            $("div.trailOptions").show();
            $("div.trailOptions").on('change', () => {
                let selectedTrail = $("#trailOptions").find(":selected").text();
                console.log(selectedTrail);
                // show the map around the location

                geocode(location, mapboxToken).then((coordinates) => {
                    console.log(coordinates);
                    let map = showMap([-98.424952,29.4046768]);
                    map.on('load', () => {
                        map.addSource('route', {
                            'type': 'geojson',
                            'data': {
                                'type': 'Feature',
                                'properties': {},
                                'geometry': {
                                    'type': 'LineString',
                                    'coordinates': [
                                        [-98.424952,29.4046768],
                                        [-98.4240498,29.4047361],
                                        [-98.4239484,29.4047545],
                                        [-98.4238706,29.4047744],
                                        [-98.4237832,29.4048099],
                                        [-98.4228486,29.4053883],
                                        [-98.4227081,29.4054843],
                                        [-98.4224765,29.4056143],
                                        [-98.422032,29.4058352],
                                        [-98.4218618,29.4059354],
                                        [-98.4215659,29.4061196],
                                        [-98.4213856,29.4062493],
                                        [-98.4207437,29.4067638],
                                        [-98.4205319,29.4069296],
                                        [-98.4203202,29.4071472],
                                        [-98.4201734,29.4072843],
                                        [-98.4201254,29.4073631],
                                        [-98.4200693,29.4074458],
                                        [-98.419965,29.407553],
                                        [-98.4196011,29.4081479],
                                        [-98.419194,29.408754],
                                        [-98.4190242,29.4090378]
                                    ]
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


