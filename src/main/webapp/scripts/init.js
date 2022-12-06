function resetCookie() {
    let cookieMessage = document.querySelector('#cookie-message');
    cookieMessage.textContent = '';
    cookieMessage.id = 'not cookie';
}

function acceptCookie() {
    $.ajax({
        type: 'GET',
        url: './server',
        data: {'cookie': 'true'},
        success: function(data) {
            updateTable(data);
        },
        error: function(data) {
            alert(data);
        }
    });

    resetCookie();
}

function denyCookie() {
    resetCookie();
}

function init() {
    document.cookie = `TIMEZONE = ${-(new Date().getTimezoneOffset())/60}`;

    if ( !document.cookie.split(';').some(c => {
        return c.trim().startsWith('ACCEPT_COOKIE' + '=');})) {
        const cookieMessage = `
        <p>Если Вы не хотите потерять свою таблицу в ближайшее время - примите cookie</p>
        <p>ПЫ. СЫ. Пока Вы не приняли cookie, введённые Вами данные не хранятся(</p><br>
        <a id='i-agree' onClick='acceptCookie()'>Хочу cookie 🍪</a>
        <a id='i-not-agree' onClick='denyCookie()'>Отстаньте со своими cookie 😑</a>
        `;
        $('#cookie-message').html(cookieMessage);
    } else {
        resetCookie();
    }

    $.ajax({
        type: 'GET',
        url: './server',
        data: {'table': 'true'},
        success: function(data) {
            updateTable(data);
            deleteDots();
        },
        error: function(data) {
            alert(data);
        }
    });
}