"use strict";

    // Display the calendar
    document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendarEvent = $("#Event").val();
    var displayDiv = $(".displayed-event");
        console.log(displayDiv);
        var displayBucket = [];

    displayDiv.each(function (index){
        // var ID = ($(this).children().eq(0));
        var name = ($(this).children().eq(0));
        var location = ($(this).children().eq(1));
        var time = ($(this).children().eq(2));
        // var meetTime = ($(this).children().eq(4));
        var date = ($(this).children().eq(3));

        var eventDisplay = {
            // "id": ID.val(),
            "title": name.val(),
            // "description": location.val(),
            "start": time.val(),
            // "meetTime": meetTime.val(),
            "date": date.val(),
        };
        displayBucket.push(eventDisplay);
    })
        console.log(displayBucket);
        var calendar = new FullCalendar.Calendar(calendarEl, {
            headerToolbar: {
                left: 'prevYear,prev,next,nextYear today',
                center: 'title',
                right: 'dayGridMonth,dayGridWeek,dayGridDay'
            },
        initialView: 'dayGridMonth',
        navLinks: true,
        dayMaxEventRows: true,
        views:{
                timeGrid:{
                    dayMaxEventRows: 6
                }
        },
        textColor: 'black',
        events: displayBucket
});
        console.log(calendar);
        calendar.render();

});

//display the title of the event, the time