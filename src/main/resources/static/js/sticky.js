"use strict";

(function() {
    //signup

    var form = document.getElementsByName('myform')[0];
    if (localStorage['username'] !== undefined) {
    var displayArea = document.getElementById('username');
    displayArea.textContent = localStorage['username'];
}
    form.addEventListener('submit', function() {
    var nameField = document.getElementsByName('username')[0];
    localStorage['username'] = nameField.value;

}, false);


    //reset password
    var form = document.getElementsByName('resetPassword')[0];
    if (localStorage['oldPassword'] !== undefined) {
        var displayArea = document.getElementById('oldPassword');
        displayArea.textContent = localStorage['oldPassword'];
    }
    form.addEventListener('submit', function() {
        var nameField = document.getElementsByName('oldPassword')[0];
        localStorage['oldPassword'] = nameField.value;
    }, false);

    // Profile setting
        var form = document.getElementsByName('profile')[0];
        if (localStorage['phoneNumber'] !== undefined) {
            var displayArea = document.getElementById('phoneNumber');
            displayArea.textContent = localStorage['phoneNumber'];
        }
        form.addEventListener('phoneNumber', function() {
            var nameField = document.getElementsByName('phoneNumber')[0];
            localStorage['phoneNumber'] = nameField.value;
        }, false);

    //create event
    var form = document.getElementsByName('event')[0];
    if (localStorage['phoneNumber'] !== undefined) {
        var displayArea = document.getElementById('phoneNumber');
        displayArea.textContent = localStorage['phoneNumber'];
    }
    form.addEventListener('phoneNumber', function() {
        var nameField = document.getElementsByName('phoneNumber')[0];
        localStorage['phoneNumber'] = nameField.value;
    }, false);

    //Edit event
    var form = document.getElementsByName('editEvent')[0];
    if (localStorage['name'] !== undefined) {
        var displayArea = document.getElementById('name');
        displayArea.textContent = localStorage['name'];
    }
    form.addEventListener('submit', function() {
        var nameField = document.getElementsByName('name')[0];
        localStorage['name'] = nameField.value;
    }, false);

    var form = document.getElementsByName('editEvent')[0];
    if (localStorage['date'] !== undefined) {
        var displayArea = document.getElementById('date');
        displayArea.textContent = localStorage['date'];
    }
    form.addEventListener('submit', function() {
        var nameField = document.getElementsByName('date')[0];
        localStorage['date'] = nameField.value;
    }, false);

    var form = document.getElementsByName('editEvent')[0];
    if (localStorage['time'] !== undefined) {
        var displayArea = document.getElementById('time');
        displayArea.textContent = localStorage['time'];
    }
    form.addEventListener('submit', function() {
        var nameField = document.getElementsByName('time')[0];
        localStorage['time'] = nameField.value;
    }, false);

    var form = document.getElementsByName('editEvent')[0];
    if (localStorage['meetTime'] !== undefined) {
        var displayArea = document.getElementById('meetTime');
        displayArea.textContent = localStorage['meetTime'];
    }
    form.addEventListener('submit', function() {
        var nameField = document.getElementsByName('meetTime')[0];
        localStorage['meetTime'] = nameField.value;
    }, false);

    var form = document.getElementsByName('editEvent')[0];
    if (localStorage['eventDetails'] !== undefined) {
        var displayArea = document.getElementById('eventDetails');
        displayArea.textContent = localStorage['eventDetails'];
    }
    form.addEventListener('submit', function() {
        var nameField = document.getElementsByName('eventDetails')[0];
        localStorage['eventDetails'] = nameField.value;
    }, false);


})();