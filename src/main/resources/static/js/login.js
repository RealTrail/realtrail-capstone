"use strict";

let path = window.location.href;

if (path.substring(path.lastIndexOf("/")) === "/signup") {
    toggle();
}

function toggle() {
    document.querySelector('.form').classList.toggle('slide');
}

var check = function() {
    if (document.getElementById('createPassword').value ===
        document.getElementById('confirmCreatePassword').value) {
        document.getElementById('alertPasswordSign').style.color = '#8CC63E';
        document.getElementById('alertPasswordSign').innerHTML = '<span class="loginspan"><i class="fas fa-check-circle"></i>Match !</span>';
    } else {
        document.getElementById('alertPasswordSign').style.color = '#EE2B39';
        document.getElementById('alertPasswordSign').innerHTML = '<span class="loginspan"><i class="fas fa-exclamation-triangle"></i>not matching</span>';
    }
}

if ($('#alertPasswordSign').text() === "Not match!") {
    $('#alertPasswordSign').css("color", '#EE2B39')
}