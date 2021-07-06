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


})();




//