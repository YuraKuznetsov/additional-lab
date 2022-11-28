const generateButton = document.querySelector(".generate__button");
const inputs = document.forms["equation"].getElementsByTagName("input");

function getRandomIntValue() {
    const min = -10, max = 10;
    return Math.floor(Math.random() * (max - min) + min);
}

function generateEquation() {
    for (let input of inputs) {
        input.value = getRandomIntValue();
    }
}

generateButton.addEventListener("click", generateEquation);

