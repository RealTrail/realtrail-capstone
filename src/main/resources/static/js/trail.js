$(".slider")
    .slick({
        slidesToShow: 3,
        slidesToScroll: 1,
        arrows: true,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 3,
                    infinite: true,
                    dots: false
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
            // You can unslick at a given breakpoint now by adding:
            // settings: "unslick"
            // instead of a settings object
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

function toggleShow () {
    var el = document.getElementById("box");
    el.classList.toggle("show");
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

// ******************** MODAL JS *******************

// Click function for show the Modal

$("#myBtn").on("click", function(){
    $(".mask").addClass("active");
});

// Function for close the Modal

function closeModal(){
    $(".mask").removeClass("active");
}

// Call the closeModal function on the clicks/keyboard

$(".close, .mask").on("click", function(){
    closeModal();
});

$(document).keyup(function(e) {
    if (e.keyCode === 27) {
        closeModal();
    }
});


// ******************** MODAL JS *******************