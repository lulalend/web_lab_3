function cleanTable() {
    const emptyTable = `
    <thead>
        <tr>
            <th>R</th>
            <th>X</th>
            <th>Y</th>
            <th>Результат</th>
            <th>Время запроса</th>
            <th>Время исполнения, мс</th>
        </tr>
    </thead>`;
    $('table.info').html(emptyTable);

    $.ajax({
        type: 'DELETE',
        url: 'server',
        success: function(data) {
            updateTable(data);
            deleteDots();
        },
        error: function(data) {
            alert(data);
        }
    });
}