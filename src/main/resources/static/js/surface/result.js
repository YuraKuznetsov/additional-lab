'use strict';

const coefficientsA = surface["coefficientsA"];
const coefficientsMeaning = {
    a11: "x²", a12: "xy", a13: "xz", a14: "x", a22: "y²", a23: "yz", a24: "y", a33: "z²", a34: "z", a44: ""
}

const I1 = surface["values"]["I1"];
const I2 = surface["values"]["I2"];
const I3 = surface["values"]["I3"];
const I4 = surface["values"]["I4"];
const K2 = surface["values"]["K2"];
const K3 = surface["values"]["K3"];
const lambda1 = surface["values"]["lambda1"];
const lambda2 = surface["values"]["lambda2"];
const lambda3 = surface["values"]["lambda3"];

const wideType = surface["wideType"];
const type = surface["type"];


function round3(number) {
    return Math.round(number * 1000) / 1000;
}

function isEmpty(value) {
    return !value || value === "0";
}

function isNotDividedCoefficient(coefficient) {
    return coefficient === "a11" || coefficient === "a22" || coefficient === "a33" || coefficient === "a44";
}

function generateBlockOfEquation(value, meaning) {
    const sign = value < 0 ? "-" : "+";
    const absoluteValue = Math.abs(value);
    return ` ${sign} ${absoluteValue}${meaning}`;
}

function generateUserEquation() {
    let userEquation = "";

    for (let coefficient in coefficientsA) {
        const value = isNotDividedCoefficient(coefficient) ? coefficientsA[coefficient] : coefficientsA[coefficient] * 2;
        if (isEmpty(value)) continue;

        const meaning = coefficientsMeaning[coefficient];
        const blockOfEquation = generateBlockOfEquation(value, meaning);
        userEquation += blockOfEquation;
    }

    if (userEquation !== "") {
        userEquation += " = 0";
    }

    if (userEquation.charAt(1) === "+") {
        userEquation = userEquation.slice(2);
    }

    return userEquation;
}

function getCoefficientsA() {
    const a11 = coefficientsA["a11"];
    const a12 = coefficientsA["a12"];
    const a13 = coefficientsA["a13"];
    const a14 = coefficientsA["a14"];
    const a22 = coefficientsA["a22"];
    const a23 = coefficientsA["a23"];
    const a24 = coefficientsA["a24"];
    const a33 = coefficientsA["a33"];
    const a34 = coefficientsA["a34"];
    const a44 = coefficientsA["a44"];

    return `a11 = ${a11}; a12 = ${a12}; a13 = ${a13}; a14 = ${a14}; a22 = ${a22}; a23 = ${a23}; a24 = ${a24}; a33 = ${a33}; a34 = ${a34}; a12 = ${a44}`
}

function generateCubicEquation() {
    return `λ³ + ${round3(-I1)}λ² + ${round3(I2)}λ + ${round3(-I3)} = 0`;
}

function generateSimpleCanonical() {
    let coefficient;

    switch (wideType) {
        case "FULL_SQUARE":
            coefficient = round3(I4 / I3);
            return `${round3(lambda1)}x² + ${round3(lambda2)}y² + ${round3(lambda3)}z² + ${(coefficient)} = 0`;
        case "CYLINDER":
            coefficient = round3(K3 / I2);
            return `${round3(lambda1)}x² + ${round3(lambda2)}y² + ${coefficient} = 0`;
        case "PARABOLOID":
            coefficient = round3(2 * Math.sqrt(-I4 / I2));
            return `${round3(lambda1)}x² + ${round3(lambda2)}y² + ${coefficient}z = 0`;
        case "PARABOLIC_CYLINDER":
            coefficient = round3(2 * Math.sqrt(-K3 / I1));
            return `${round3(lambda1)}x² + ${coefficient}y = 0`;
        default:
            return "Не підтримується";
    }
}

function generateCanonical() {
    let coefficient;

    switch (wideType) {
        case "FULL_SQUARE":
            coefficient = I4 / I3;
            if (coefficient === 0.0) {
                return round3(lambda1) + "x² + " + round3(lambda2) + "y² + " + round3(lambda3) + "z² = 0";
            }
            return round3(lambda1 / -coefficient) + "x² + "
                + round3(lambda2 / -coefficient) + "y² + "
                + round3(lambda3 / -coefficient) + "z² = 1";
        case "CYLINDER":
            coefficient = K3 / I2;
            return round3(lambda1 / -coefficient) + "x² + "
                + round3(lambda2 / -coefficient) + "y² = 1";
        case "PARABOLOID":
            coefficient = round3(2 * Math.sqrt(-I4 / I2));
            return round3(lambda1) + "x² + " + round3(lambda2) + "y² + " + coefficient + "z = 0";
        case "PARABOLIC_CYLINDER":
            coefficient = round3(2 * Math.sqrt(-K3 / I1));
            return round3(lambda1) + "x² + " + coefficient + "y = 0";
        default:
            return "Не підтримується";
    }
}


function getInformationI() {
    return `I1 = ${round3(I1)}; I2 = ${round3(I2)}; I3 = ${round3(I3)}; I4 = ${round3(I4)}`;
}

function getInformationK() {
    return `K2 = ${round3(K2)}; K3 = ${round3(K3)}`;
}

function getLambdasInfo() {
    return `λ1 = ${round3(lambda1)}; λ2 = ${round3(lambda2)}; λ3 = ${round3(lambda3)}`;
}

function getCanonicalExplain() {
    switch (wideType) {
        case "FULL_SQUARE":
            return "Оскільки I₃ не дорівнює нулеві, канонічне рівняння має вигляд:";
        case "PARABOLOID":
            return "Оскільки I₃ == 0, I₂ != 0, I₄ != 0, канонічне рівняння має вигляд:";
        case "CYLINDER":
            return "Оскільки I₃ == 0 && I₂ != 0 && I₄ == 0, канонічне рівняння має вигляд:";
        case "PARABOLIC_CYLINDER":
            return "Оскільки I₃ == 0 && I₂ == 0 && I₄ == 0 && I₁ != 0 && K₃ != 0, канонічне рівняння має вигляд:";
        default:
            return "Жодна з умов не виконується. Канонічного рівняння не існує";
       }
}

function getCanonicalFormula() {
    switch (wideType) {
        case "FULL_SQUARE":
            return "λ₁x² + λ₂y² + λ₃z² + I₄ / I₃ = 0";
        case "PARABOLOID":
            return "λ₁x² + λ₂y² + 2z * (-I₄ / I₂)^(1/2) = 0";
        case "CYLINDER":
            return "λ₁x² + λ₂y² + K₃ / I₂ = 0";
        case "PARABOLIC_CYLINDER":
            return "λ₁x² + 2y * (-K₃ / I₁)^(1/2) = 0";
        default:
            return "...";
       }
}

function getSrc() {
    const pathToFolder = "/images/surfaces/";

    switch (type) {
        case "Еліпсоїд":
        case "Уявний еліпсоїд":
        case "Сфера":
        case "Сфероїд":
            return pathToFolder + "Ellipsoid.png";
        case "Гіперболоїд однолистовий":
            return pathToFolder + "Hyperboloid_1.png";
        case "Гіперболоїд дволистовий":
            return pathToFolder + "Hyperboloid_2.png";
        case "Конус":
            return pathToFolder + "Cone.png";
        case "Коловий параболоїд":
        case "Еліптичний параболоїд":
            return pathToFolder + "Elliptic_Paraboloid.png";
        case "Гіперболічний параболоїд":
            return pathToFolder + "Hyperbolic_Paraboloid.png";
        case "Коловий циліндр":
        case "Еліптичний циліндр":
            return pathToFolder + "Elliptic_Cylinder.png";
        case "Гіперболічний циліндр":
            return pathToFolder + "Hyperbolic_Cylinder.png";
        case "Параболічний циліндр":
            return pathToFolder + "Parabolic_Cylinder.png";
        default:
            return "";
       }
}

function showInfo() {
    document.querySelector("#coefficientsA").innerHTML = getCoefficientsA();
    document.querySelector("#userEquation").innerHTML = generateUserEquation();
    document.querySelector("#informationI").innerHTML = getInformationI();
    document.querySelector("#informationK").innerHTML = getInformationK();
    document.querySelector("#cubicEquation").innerHTML = generateCubicEquation();
    document.querySelector("#lambdasInfo").innerHTML = getLambdasInfo();
    document.querySelector("#explain").innerHTML = getCanonicalExplain();
    document.querySelector("#canonicalFormula").innerHTML = getCanonicalFormula();
    document.querySelector("#simpleCanonical").innerHTML = generateSimpleCanonical();
    document.querySelector("#canonical").innerHTML = generateCanonical();
    document.querySelector("#surfaceType").innerHTML = type;
    document.querySelector("#surface-graph").src = getSrc();
}

showInfo();





