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


// ******************** Trail Search ***************

// Filter by difficulty level
$(document).ready(function () {
    // let trails = [];
    //
    // $("#difficultyLevel").on("change", function() {
    //     $(".slick-slide").each(function (i){
    //         var diffLevel = $(this).find(".header > *:nth-child(2)").text().split(":")[1].trim();
    //         if ($("#difficultyLevel :selected").text() !== diffLevel){
    //             $(this).addClass("hidden");
    //         } else {
    //             $(this).removeClass("hidden");
    //         }
    //     })
    // })
    //
    // $("#type").on("change", function() {
    //     $(".slick-slide").each(function (i){
    //         var routeType = $(this).find(".header > *:nth-child(4)").text().split(":")[1].trim();
    //         console.log(routeType);
    //         if ($("#type :selected").text() !== routeType){
    //             $(this).addClass("hidden");
    //         } else {
    //             $(this).removeClass("hidden");
    //         }
    //     })
    // })

});

