const surfaces = document.querySelectorAll(".surface");

function showSurface(surface) {
    const header = surface.querySelector(".surface__header");
    const icon = surface.querySelector(".surface__icon");
    const body = surface.querySelector(".surface__body");

    header.classList.add("fill-bg");
    body.classList.add("shown");
    icon.classList.add("turn-over");
}

function closeSurface(surface) {
    const header = surface.querySelector(".surface__header");
    const icon = surface.querySelector(".surface__icon");
    const body = surface.querySelector(".surface__body");

    header.classList.remove("fill-bg");
    body.classList.remove("shown");
    icon.classList.remove("turn-over");
}

function isSurfaceBodyShown(surface) {
    const surfaceBody = surface.querySelector(".surface__body");
    return surfaceBody.classList.contains("shown");
}

function addListener(surface) {
    const surfaceHeader = surface.querySelector(".surface__header");

    surfaceHeader.addEventListener("click", () => {
        if (isSurfaceBodyShown(surface)) {
            closeSurface(surface);
        } else {
            showSurface(surface)
        }
    });
}

for (let surface of surfaces) {
    addListener(surface);
}