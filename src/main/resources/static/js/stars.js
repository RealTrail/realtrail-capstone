$('.star-input').click(function() {
    $(this).parent()[0].reset();
    var prevStars = $(this).prevAll();
    var nextStars = $(this).nextAll();
    prevStars.attr('checked',true);
    nextStars.attr('checked',false);
    $(this).attr('checked',true);
});

$('.star-input-label').on('mouseover',function() {
    var prevStars = $(this).prevAll();
    prevStars.addClass('hovered');
});
$('.star-input-label').on('mouseout',function(){
    var prevStars = $(this).prevAll();
    prevStars.removeClass('hovered');
});




$(document).ready(() => {
    let commentsLength = $("#commentsNumber").val();

    for (let i = 0; i < commentsLength; i++) {
        let rating = $(".userRating").eq(i).val();
        for (let j = 0; j < 5; j++) {
            if (j <= rating) {
                $(".star").eq(j).css('color', 'orange');
                console.log(i, j, rating);
            }
        }
    }




});