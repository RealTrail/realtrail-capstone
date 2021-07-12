"use strict";

    // Display the calendar
    document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendarEvent = $("#Event").val();
    var displayDiv = $(".displayed-event");
        console.log(displayDiv);
        var displayBucket = [];

    displayDiv.each(function (index){
        var ID = ($(this).children().eq(0));
        var name = ($(this).children().eq(1));
        var location = ($(this).children().eq(2));
        var time = ($(this).children().eq(3));
        // var meetTime = ($(this).children().eq(4));
        var date = ($(this).children().eq(4));

        var eventDisplay = {
            "id": ID.val(),
            "title": name.val(),
            // "description": location.val(),
            "start": time.val(),
            // "meetTime": meetTime.val(),
            "date": date.val(),
            "url": "/events/" + ID.val()
        };
        displayBucket.push(eventDisplay);
    })
        console.log(displayBucket);

        var calendar = new FullCalendar.Calendar(calendarEl, {
            eventClick: function(info) {
                var eventObj = info.event;

                if (eventObj.url) {
                    window.open(eventObj.url);
                    info.jsEvent.preventDefault(); // prevents browser from following link in current tab.
                } else {
                    alert('Clicked ' + eventObj.title);
                }
            },
            headerToolbar: {
                left: 'prev,next, today',
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