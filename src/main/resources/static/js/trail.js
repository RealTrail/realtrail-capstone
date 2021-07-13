$(".slider")
    .slick({
        infinite: true,
        slidesToShow: 6,
        slidesToScroll: 1,
        arrows: true,
        autoplay: false,
        responsive: [
            {
                breakpoint: 1800,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 1200,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 1008,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 600,
                arrows:false,
                settings: "unslick"
            }

        ]
    })
    .on("setPosition", function () {
        resizeSlider();
    });

$(window).on("resize", function (e) {
    resizeSlider();
});

var slickHeight = $(".slick-track").outerHeight();

var slideHeight = $(".slick-track").find(".slick-slide").outerHeight();

function resizeSlider() {
    $(".slick-track")
        .find(".slick-slide .slide-wrap")
        .css("height", slickHeight + "px");
}


$("body, #mainContainer").on("scroll", function() {
    $("body, #mainContainer").scrollTop($(this).scrollTop());
});


// ********************************** SIDE NAV *******************************

function openNav() {

    document.getElementById("mySidenav").style.width = "80%";
    document.getElementById("main").style.marginLeft = "80%";
    document.getElementById("mySidenav").classList.add('puff-in-center');
    // document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    document.getElementById("mySidenav").classList.remove('puff-in-center');
    // document.body.style.backgroundColor = "transparent";
}





$(document).ready(function () {

    // $(".slider > div").each(() => {
    //     // grab all the trail info form trail card
    //     let hrefArr = $(this).find("a").attr("href").split("/");
    //     let imageUrl = $(this).find(".image > img").attr("src");
    //     let name = $(this).find(".header >  *:nth-child(1)").text();
    //     let diffLevel = $(this).find(".header > *:nth-child(2)").text().split(":")[1].trim();
    //     let length = $(this).find(".header > *:nth-child(3)").text().split(":")[1].trim();
    //     let type = $(this).find(".header > *:nth-child(4)").text().split(":")[1].trim();
    //
    //     let trail = {
    //
    //         imageUrl: imageUrl,
    //         name: name,
    //         difficultyLevel: diffLevel,
    //         length: length,
    //         type: type
    //     };
    //
    //     console.log(trail);
    //     trails.push(trail);
    // });
    // console.log(trails);



    // search
    $("#search").click((e) => {
        e.preventDefault();
        let keyword = $("#keyword").val();
        console.log(keyword);
        if (keyword !== "") {
            $.ajax({
                type: "GET",
                url: "/search-trail?keyword=" + keyword,
                dataType: "json",
                success: (trailArr) => {
                    console.log(trailArr);
                    if (trailArr.length === 0) {
                        $(".slider-container .slider").html(`<h2>Nothing found. Search for another trail?</h2>`);
                    } else {
                        for (let trail of trailArr) {
                            $(".slider-container .slider").html(
                                `<div>
                                    <a href=${'/trails/' + trail.id}>
                                        <div class="slide-wrap">
                                            <div class="image">
                                                <img class="image" src=${trail.trailImages[0].pictureUrl} alt="trail image">
                                            </div>
                                            <div class="header">
                                                <h3>${trail.name}</h3>
                                                <h4>${'Difficulty Level:  ' + trail.difficultyLevel}</h4>
                                                <h4>${'Trail length (miles):  ' + trail.length}</h4>
                                                <h4>${'Trail route type:  ' + trail.type}</h4>
                                            </div>
                                        </div>
                                    </a>
                                </div>`
                            );
                        }
                    }
                },
                error: (error) => {
                    console.log("Error connecting the server");
                    console.log(error);
                    // window.location = "/error";
                }
            });
        }
    });

    // Filter by difficulty level

    $("#difficultyLevel").on("change", function() {
        let diffLevel = $("#difficultyLevel").find(":selected").val();
        if ($("type").find(":selected").val() === "") {
            $.ajax({
                type: "GET",
                url: "/trails/filter?difficultyLevel=" + diffLevel,
                dataType: "json",
                success: (trailArr) => {
                    console.log(trailArr);


                },
                error: (error) => {
                    console.log("Error connecting the server");
                    console.log(error);
                    // window.location = "/error";
                }
            });
        }
    });

    // Filter by type
    $("#type").on("change", () => {
        let type = $("type").find(":selected").val();
        if ($("#difficultyLevel").find(":selected").val() === "") {
            $.ajax({
                type: "GET",
                url: "/trails/filter?type=" + type,
                dataType: "json",
                success: (trailArr) => {
                    console.log(trailArr);


                },
                error: (error) => {
                    console.log("Error connecting the server");
                    console.log(error);
                    // window.location = "/error";
                }
            });
        } else {
            let diffLevel = $("#difficultyLevel").find(":selected").val();
            $.ajax({
                type: "GET",
                url: "/trails/filter?difficultyLevel=" + diffLevel + "&type=" + type,
                dataType: "json",
                success: (trailArr) => {
                    console.log(trailArr);


                },
                error: (error) => {
                    console.log("Error connecting the server");
                    console.log(error);
                    // window.location = "/error";
                }
            });
        }
    })



    // $("#difficultyLevel").on("change", function() {
        // $(".slick-slide").each(() => {
        //     let diffLevel = $(this).find(".header > *:nth-child(2)").text().split(":")[1].trim();
        //     console.log(diffLevel);
        // if ($("#difficultyLevel :selected").text() !== diffLevel){
        //     $(this).hide()
        // } else {
        //     $(this).show()
        // }
        // })
    // }


    // $("#type").on("change", function() {
    //     $(".slick-slide").each(function (i){
    //         var routeType = $(this).find(".header > *:nth-child(4)").text().split(":")[1].trim();
    //         console.log(routeType);
    //         if ($("#type :selected").text() !== routeType){
    //             $(this).hide()
    //         } else {
    //             $(this).show()
    //         }
    //     })
    // })

});

