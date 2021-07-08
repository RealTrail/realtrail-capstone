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

// ******************** Trail Search ***************

// Filter by difficulty level
$(document).ready(function () {
    let trails = [];
    //
    // $("img.image").each(function () {
    //     console.log($(this).attr("src"));
    // });
    //
    // $("img.image").map(function(index, element) {
    //     console.log(index, element.attr("src"));
    // });

    // $(".header > h3")  // an array of names

    // an array of difficulty levels

    // an array of length

    // make trail obj
    //for loop
   // let trail = {name: // nameArr[i], difficultyLevel: difficultylevelArr[i], ...}
   // trails.push(trail)  ==> after for loop, you get all the trails

    $("#difficultyLevel").on("change", function() {
        $("#type").hide()
        $(".slick-slide").each(function (i){
            var diffLevel = $(this).find(".header > *:nth-child(2)").text().split(":")[1].trim();
            if ($("#difficultyLevel :selected").text() !== diffLevel){
                $(this).css("display", "none")
            } else {
                $(this).css("display", "block")
            }
        })
    })

    $("#type").on("change", function() {
        $("#difficultyLevel").hide()
        $(".slick-slide").each(function (i){
            var routeType = $(this).find(".header > *:nth-child(4)").text().split(":")[1].trim();
            console.log(routeType);
            if ($("#type :selected").text() !== routeType){
                $(this).css("display", "none")
            } else {
                $(this).css("display", "block")
            }
        })
    })

});

