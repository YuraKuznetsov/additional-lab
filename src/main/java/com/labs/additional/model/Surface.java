package com.labs.additional.model;

public class Surface {
    private final String userEquation;
    private final String canonicalEquation;
    private final String surfaceType;
    private final String imgSrc;

    public Surface(String userEquation, String canonicalEquation, String surfaceType, String imgSrc) {
        this.userEquation = userEquation;
        this.canonicalEquation = canonicalEquation;
        this.surfaceType = surfaceType;
        this.imgSrc = imgSrc;
    }

    public String getUserEquation() {
        return userEquation;
    }

    public String getCanonicalEquation() {
        return canonicalEquation;
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
