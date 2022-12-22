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

function showSurfaces(surfaces) {
    alert("Surfaces: " + surfaces[0].equation);
}


//////////////////////////////////////

const example1 = document.querySelector("#example1");
const example2 = document.querySelector("#example2");
const example3 = document.querySelector("#example3");
const example4 = document.querySelector("#example4");
const example5 = document.querySelector("#example5");

example1.addEventListener("click", () => {
    inputs[0].value = "3";
    inputs[1].value = "2";
    inputs[2].value = "6";
    inputs[3].value = "-2";
    inputs[4].value = "";
    inputs[5].value = "";
    inputs[6].value = "";
    inputs[7].value = "";
    inputs[8].value = "4";
    inputs[9].value = "-12";
});

example2.addEventListener("click", () => {
    inputs[0].value = "3";
    inputs[1].value = "1";
    inputs[2].value = "";
    inputs[3].value = "";
    inputs[4].value = "";
    inputs[5].value = "";
    inputs[6].value = "";
    inputs[7].value = "";
    inputs[8].value = "-5";
    inputs[9].value = "1";
});

example3.addEventListener("click", () => {
    inputs[0].value = "2";
    inputs[1].value = "-11";
    inputs[2].value = "";
    inputs[3].value = "";
    inputs[4].value = "";
    inputs[5].value = "";
    inputs[6].value = "";
    inputs[7].value = "";
    inputs[8].value = "3";
    inputs[9].value = "-9";
});

example4.addEventListener("click", () => {
    inputs[0].value = "-3";
    inputs[1].value = "";
    inputs[2].value = "4";
    inputs[3].value = "";
    inputs[4].value = "";
    inputs[5].value = "22";
    inputs[6].value = "";
    inputs[7].value = "";
    inputs[8].value = "";
    inputs[9].value = "10";
});

example5.addEventListener("click", () => {
    inputs[0].value = "-5";
    inputs[1].value = "23";
    inputs[2].value = "3";
    inputs[3].value = "3";
    inputs[4].value = "";
    inputs[5].value = "1";
    inputs[6].value = "";
    inputs[7].value = "";
    inputs[8].value = "6";
    inputs[9].value = "7";
});