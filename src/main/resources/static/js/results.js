const surfaces = document.querySelectorAll(".surface");

for (let surface of surfaces) {
    const header = surface.querySelector(".surface__header");
    const icon = surface.querySelector(".surface__icon");
    const body = surface.querySelector(".surface__body");

    header.addEventListener("click", e => {
        if (body.classList.contains("shown")) {
            header.classList.remove("fill-bg");
            body.classList.remove("shown");
            icon.classList.remove("turn-over");
        } else {
            header.classList.add("fill-bg");
            body.classList.add("shown");
            icon.classList.add("turn-over");
        }
        
    });
}

function showBody(body) {
    body.style.opacity = "1";
    body.style.height = "auto";
}

function hideBody(body) {
    body.style.opacity = "0";
    body.style.height = "0";
    body.style.overflow = "hidden";
}