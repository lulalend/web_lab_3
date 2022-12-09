function handleClick() {
    let target = $("#svg");
    let r = parseFloat($('[id="feedback-form:R_input"]').val());
    let x = event.clientX - target.position().left;
    let y = event.clientY - target.position().top;
    let xValue = ((x - 150) / (100 / r) + 2*r/3).toFixed(3);
    let yValue = Math.round(((y - 150 + $(window).scrollTop()) / (-100 / r))) +4;

    let ySelect = "feedback-form:Y:"+ yValue;
    $('[id="feedback-form:X"]').val(xValue);
    document.getElementById(ySelect).click();
    $('[id="feedback-form:check"]').click();
}
