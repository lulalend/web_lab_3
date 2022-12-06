let form = document.querySelector('form');
let R = form.elements.R;
let rError = document.querySelector('span.r-error');
let X = form.elements.X;
let xError = document.querySelector('span.x-error');
let Y = form.elements.Y;
let yError = document.querySelector('span.y-error');

const NUMBER_REG_EXP = /^([-+]?\d+[.,]\d+|[-+]?\d+|[-+]?\d+([.,]\d+)?e[-+]?\d+)$/;

function showMissingError(param, paramError) {
    paramError.textContent = `Задайте ${param}, пожалуйста`;
    paramError.className = 'error';
}

function showNumberFormatError(param, paramError) {
    paramError.textContent = `Представьте ${param}, как число в привычном виде`;
    paramError.className = 'error';
}

function showArithmeticError(param, paramError) {
    paramError.textContent = `Обратите внимание на пределы ${param}`;
    paramError.className = 'error';
}

function submitForm() {
    const missingR = R.validity.valueMissing;
    const missingX = X.value.trim() === "";
    const numberFormatX = !NUMBER_REG_EXP.test(X.value.trim());
    const arithmeticX = parseFloat(X.value) <= -5 || parseFloat(X.value) >= 3;
    const missingY = Y.validity.valueMissing;

    rError.textContent = '';
    rError.className = 'not error';
    yError.textContent = '';
    yError.className = 'not error';
    xError.textContent = '';
    xError.className = 'not error';

    if (missingX || missingY || missingR || numberFormatX ||  arithmeticX) {
        if(missingR) {
            showMissingError('R', rError);
        } else if (missingX) {
            showMissingError('X', xError);
        } else if (numberFormatX) {
            showNumberFormatError('X', xError);
        } else if (arithmeticX) {
            showArithmeticError('X', xError);
        } else {
            showMissingError('Y', yError);
        }
    } else {
        $.ajax({
            type: 'GET',
            url: './server',
            contentType: "application/json",
            data: {'X': X.value.replace(',', '.'), 'Y': Y.value,
                'R': R.value},
            success: function(data) {
                updateTable(data);
            },
            error: function(data) {
                alert(data);
            }
        });
    }
}