const TIME_REG_EXP = /\d{2}:\d{2}:\d{2}/;

function parseDate(str) {
    return str.match(TIME_REG_EXP);
}

function updateTable(data) {
    let table = ``;
    for (let i = 0; i < data.length; i++) {
        draw(data[i].r, data[i].x, data[i].y, data[i].resultHit);
        table = `
        <tr>
            <td>${data[i].r}</td>
            <td>${data[i].x}</td>
            <td>${data[i].y}</td>
            <td>${data[i].resultHit}</td>
            <td>${parseDate(JSON.stringify(data[i].clientDate))}</td>
            <td>${data[i].executionTime}</td>
        </tr>
        `;
        $('table.info tr:first').after(table);
    }
}