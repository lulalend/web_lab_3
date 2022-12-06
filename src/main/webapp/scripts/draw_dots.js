let container = document.querySelector('svg');
let dots = [];

function deleteDots() {
    for (let i = dots.length - 1; i >= 0; i--) {
        container.removeChild(dots[i]);
        dots.pop();
    }
}

function draw(rValue, xValue, yValue, hit) {
    const r = document.querySelector('form').elements.R;

    if ( !r.validity.valueMissing && parseFloat(r.value) === rValue) {
        let svgns = 'http://www.w3.org/2000/svg';
        let circle = document.createElementNS(svgns, 'circle');
        circle.setAttributeNS(null, 'cx', 150 + xValue * 200 / (2*rValue) );
        circle.setAttributeNS(null, 'cy', 150 - yValue * 200 / (2*rValue));
        circle.setAttributeNS(null, 'r', 4);
        circle.setAttributeNS(null, 'style', 'fill: black; stroke: black');
        dots.push(circle);
        container.appendChild(circle);
        //let color = hit ? '#32CD32' : '#FF0000';
    }
}
