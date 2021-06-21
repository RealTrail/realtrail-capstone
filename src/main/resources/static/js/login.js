document.querySelectorAll('.toggle-slide').forEach(e => e.addEventListener('click', toggle));

document.querySelectorAll('form').forEach(f => f.addEventListener('submit', (e) => e.preventDefault()) )

function toggle(e) {
    e.preventDefault();
    document.querySelector('.form').classList.toggle('slide');
}

var check = function() {
    if (document.getElementById('password').value ==
        document.getElementById('checkPassword').value) {
        document.getElementById('alertPassword').style.color = '#8CC63E';
        document.getElementById('alertPassword').innerHTML = '<span><i class="fas fa-check-circle"></i>Match !</span>';
    } else {
        document.getElementById('alertPassword').style.color = '#EE2B39';
        document.getElementById('alertPassword').innerHTML = '<span><i class="fas fa-exclamation-triangle"></i>not matching</span>';
    }
}

var checkSign = function() {
    if (document.getElementById('createPassword').value ==
        document.getElementById('confirmCreatePassword').value) {
        document.getElementById('alertPasswordSign').style.color = '#8CC63E';
        document.getElementById('alertPasswordSign').innerHTML = '<span><i class="fas fa-check-circle"></i>Match !</span>';
    } else {
        document.getElementById('alertPasswordSign').style.color = '#EE2B39';
        document.getElementById('alertPasswordSign').innerHTML = '<span><i class="fas fa-exclamation-triangle"></i>not matching</span>';
    }
}