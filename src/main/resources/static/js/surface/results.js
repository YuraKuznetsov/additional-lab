class SurfaceMenu {
    constructor(surface) {
        this.header = surface.querySelector(".surface__header");
        this.icon = surface.querySelector(".surface__icon");
        this.body = surface.querySelector(".surface__body");
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
}

const surfacesList = document.querySelector(".surfaces-list");
const surfaces = document.querySelectorAll(".surface");

for (let surface of surfaces) {
    new SurfaceMenu(surface).activate();
}

if (surfaces.length === 0) {
    surfacesList.innerHTML = "Пусто... Ви ще не визначали жодної поверхні."
}