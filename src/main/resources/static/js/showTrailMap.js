"use strict";

$(document).ready(() => {
    let id = $("#trailId").val();
    // get the trail location coordinates
    let trailPoint = $("#trailLocation").val().split(",");
    console.log(trailPoint);

    let coordinates = [];

    // get trail map
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

                $("#trail-map").css("height", "700px");
                // show the map around the location
                let map = new mapboxgl.Map({
                    container: 'trail-map',
                    style: 'mapbox://styles/mapbox/outdoors-v11',
                    center: trailPoint,
                    zoom: 15,
                    minZoom: 11
                });


                if (id <= 21) {  // existing trails form db
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
                } else {  // trails created by users
                    map.on('load', () => {
                        let trailToSearch = coordinates.join(';');
                        console.log(trailToSearch);

                        // make a directions request
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


    // click to add rating
    $('.star-input').click(function() {
        $(this).parent()[0].reset();
        var prevStars = $(this).prevAll();
        var nextStars = $(this).nextAll();
        prevStars.attr('checked',true);
        nextStars.attr('checked',false);
        $(this).attr('checked',true);

        let rating = $(this).attr("data-value");
        console.log(rating);
        $("#rating").val(rating);
    });

    $('.star-input-label').on('mouseover',function() {
        var prevStars = $(this).prevAll();
        prevStars.addClass('hovered');
    });
    $('.star-input-label').on('mouseout',function(){
        var prevStars = $(this).prevAll();
        prevStars.removeClass('hovered');
    });


    let commentsLength = $("#commentsNumber").val();

    for (let i = 0; i < commentsLength; i++) {
        let rating = $(".userRating").eq(i).val();
        for (let j = 0; j <= 4; j++) {
            if (j <= rating - 1) {
                $(".star").eq(5 * i + j).css('color', 'orange');
            }
        }
    }
});

mapboxgl.accessToken = mapboxToken;

