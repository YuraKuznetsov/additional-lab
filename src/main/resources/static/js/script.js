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


const submitButton = document.querySelector(".submit__button");

function isImportantCoefficient() {
    for (let i = 0; i < 6; i++) {
        let value = inputs[i].value;
        console.log(value);
        if (value != "" && value != "0") {
            return true;
        }
    }
    return false;
}



function checkForm(event) {
    if (!isImportantCoefficient()) {
        event.preventDefault();
        alert("Ви не ввели необхідних коефіціентів");
    }
}

submitButton.addEventListener("click", checkForm);