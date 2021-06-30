"use strict";

    // Display the calendar
    document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = $('#calendar');
    var calendarEvent = $("#Event").value;
    var displayDiv = $(".displayed-event");
    var displayBucket = [];

    displayDiv.each(function (index){
        var ID = ($(this).children().eq(0));
        var name = ($(this).children().eq(1));
        var location = ($(this).children().eq(2));
        var time = ($(this).children().eq(3));
        var meetTime = ($(this).children().eq(4));
        var date = ($(this).children().eq(5));

        var eventDisplay = {
            "id": ID.val(),
            "name": name.val(),
            "location": location.val(),
            "time": time.val(),
            "meetTime": meetTime.val(),
            "date": date.val(),
        };
        displayBucket.push(eventDisplay);
    })

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: displayBucket

});
    calendar.render();
    // calendar.updateSize();
});

//display the title of the event, the time