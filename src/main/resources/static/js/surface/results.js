class SurfaceMenu {
    constructor(surfaceInfo) {
        this.surfaceInfo = surfaceInfo;
        this.icon = this.generateIcon();
        this.header = this.generateHeader();
        this.body = this.generateBody();
    }

    generateIcon() {
        const iconBlock = document.createElement("div");
        iconBlock.classList.add("surface__icon");

        const icon = document.createElement("i");
        icon.classList.add("fa-solid", "fa-caret-down");

        iconBlock.appendChild(icon);

        return iconBlock;
    }

    generateHeader() {
        const header = document.createElement("div");
        header.classList.add("surface__header");

        const userEquation = document.createElement("div");
        userEquation.classList.add("surface__equation");
        const userEquationText = document.createTextNode(this.getUserEquation());
        userEquation.appendChild(userEquationText);

        header.appendChild(userEquation);
        header.appendChild(this.icon);

        return header;
    }

    generateBody() {
        const body = document.createElement("div");
        body.classList.add("surface__body");

        const imgDiv = document.createElement("div");
        imgDiv.classList.add("surface__image");

        const img = document.createElement("img");
        img.src = this.getSrc();

        imgDiv.appendChild(img);
        body.appendChild(this.generateSurfaceInfo());
        body.appendChild(imgDiv);

        return body;
    }

    generateSurfaceInfo() {
        const surfaceInfo = document.createElement("div");
        surfaceInfo.classList.add("surface__info");

        surfaceInfo.appendChild(this.generateCanonicalDiv());
        surfaceInfo.appendChild(this.generateTypeDiv());

        return surfaceInfo;
    }

    generateCanonicalDiv() {
        const canonicalEquationDiv = document.createElement("div");
        canonicalEquationDiv.classList.add("surface__canonical");

        const canonicalEquationLabel = document.createElement("div");
        canonicalEquationLabel.appendChild(document.createTextNode("?????????????????? ????????????????:"));

        const canonicalEquation = document.createElement("div");
        canonicalEquation.appendChild(document.createTextNode(this.getCanonical()));

        canonicalEquationDiv.appendChild(canonicalEquationLabel);
        canonicalEquationDiv.appendChild(canonicalEquation);

        return canonicalEquationDiv;
    }

    generateTypeDiv() {
        const typeDiv = document.createElement("div");
        typeDiv.classList.add("surface__type");

        const typeLabel = document.createElement("div");
        typeLabel.appendChild(document.createTextNode("?????? ????????????????:"));

        const type = document.createElement("div");
        type.appendChild(document.createTextNode(this.surfaceInfo["type"]));

        typeDiv.appendChild(typeLabel);
        typeDiv.appendChild(type);

        return typeDiv;
    }

    getSrc() {
        const pathToFolder = "/images/surfaces/";

        switch (this.surfaceInfo["type"]) {
            case "????????????????":
            case "???????????? ????????????????":
            case "??????????":
            case "??????????????":
                return pathToFolder + "Ellipsoid.png";
            case "?????????????????????? ????????????????????????":
                return pathToFolder + "Hyperboloid_1.png";
            case "?????????????????????? ??????????????????????":
                return pathToFolder + "Hyperboloid_2.png";
            case "??????????":
                return pathToFolder + "Cone.png";
            case "?????????????? ????????????????????":
            case "???????????????????? ????????????????????":
                return pathToFolder + "Elliptic_Paraboloid.png";
            case "?????????????????????????? ????????????????????":
                return pathToFolder + "Hyperbolic_Paraboloid.png";
            case "?????????????? ??????????????":
            case "???????????????????? ??????????????":
                return pathToFolder + "Elliptic_Cylinder.png";
            case "?????????????????????????? ??????????????":
                return pathToFolder + "Hyperbolic_Cylinder.png";
            case "???????????????????????? ??????????????":
                return pathToFolder + "Parabolic_Cylinder.png";
            default:
                return "";
        }
    }

    getCanonical() {
        const values = this.surfaceInfo["values"];
        let coefficient;

        switch (this.surfaceInfo["wideType"]) {
            case "FULL_SQUARE":
                coefficient = values["I4"] / values["I3"];
                if (coefficient === 0.0) {
                    return this.round3(values["lambda1"]) + "x?? + "
                        + this.round3(values["lambda2"]) + "y?? + "
                        + this.round3(values["lambda3"]) + "z?? = 0";
                }

                return this.round3(values["lambda1"] / -coefficient) + "x?? + "
                    + this.round3(values["lambda2"] / -coefficient) + "y?? + "
                    + this.round3(values["lambda3"] / -coefficient) + "z?? = 1";

            case "CYLINDER":
                coefficient = values["K3"] / values["I2"];
                return this.round3(values["lambda1"] / -coefficient) + "x?? + "
                    + this.round3(values["lambda2"] / -coefficient) + "y?? = 1";

            case "PARABOLOID":
                coefficient = this.round3(2 * Math.sqrt(-values["I4"] / values["I2"]));
                return this.round3(values["lambda1"]) + "x?? + " + this.round3(values["lambda2"]) + "y?? + " + coefficient + "z = 0";

            case "PARABOLIC_CYLINDER":
                coefficient = this.round3(2 * Math.sqrt(-values["K3"] / values["I1"]));
                return this.round3(values["lambda1"]) + "x?? + " + coefficient + "y = 0";

            default:
                return "???? ??????????????????????????";
        }
    }

    getUserEquation() {
        const coefficientsA = this.surfaceInfo["coefficientsA"];
        const coefficientsMeaning = {
            a11: "x??", a12: "xy", a13: "xz", a14: "x", a22: "y??", a23: "yz", a24: "y", a33: "z??", a34: "z", a44: ""
        }
        let userEquation = "";

        for (let coefficient in coefficientsA) {
            const value = this.isNotDividedCoefficient(coefficient) ? coefficientsA[coefficient] : coefficientsA[coefficient] * 2;
            if (this.isEmpty(value)) continue;

            const meaning = coefficientsMeaning[coefficient];
            const blockOfEquation = this.generateBlockOfEquation(value, meaning);
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

    isEmpty(value) {
        return !value || value === "0";
    }

    isNotDividedCoefficient(coefficient) {
        return coefficient === "a11" || coefficient === "a22" || coefficient === "a33" || coefficient === "a44";
    }

    generateBlockOfEquation(value, meaning) {
        const sign = value < 0 ? "-" : "+";
        const absoluteValue = Math.abs(value);
        return ` ${sign} ${absoluteValue}${meaning}`;
    }

    round3(number) {
        return Math.round(number * 1000) / 1000;
    }

    showBody() {
        this.header.classList.add("fill-bg");
        this.body.classList.add("shown");
        this.icon.classList.add("turn-over");
    }

    hideBody() {
        this.header.classList.remove("fill-bg");
        this.body.classList.remove("shown");
        this.icon.classList.remove("turn-over");
    }

    isBodyShown() {
        return this.body.classList.contains("shown");
    }

    activate() {
        this.header.addEventListener("click", () => {
            if (this.isBodyShown()) {
                this.hideBody();
            } else {
                this.showBody();
            }
        });
    }

    getSurfaceMenu() {
        const surfaceMenu = document.createElement("div");
        surfaceMenu.classList.add("surface");
        surfaceMenu.appendChild(this.header);
        surfaceMenu.appendChild(this.body);

        return surfaceMenu;
    }
}

window.onload = () => {
    const surfacesList = document.querySelector("#list");

    for (let surfaceInfo of allSurfaces) {
        const surfaceMenu = new SurfaceMenu(surfaceInfo);
        surfaceMenu.activate();
        surfacesList.appendChild(surfaceMenu.getSurfaceMenu())
    }

    if (!surfacesList.hasChildNodes()) {
        const textNode = document.createTextNode("??????????... ???? ???? ???? ?????????????????? ???????????? ????????????????.");
        surfacesList.appendChild(textNode);
    }
}