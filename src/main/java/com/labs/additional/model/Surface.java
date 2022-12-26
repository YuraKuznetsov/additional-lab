package com.labs.additional.model;

public class Surface {
    private final String userEquation;
    private final String canonicalEquation;
    private final String surfaceType;

    public Surface(String userEquation, String canonicalEquation, String surfaceType) {
        this.userEquation = userEquation;
        this.canonicalEquation = canonicalEquation;
        this.surfaceType = surfaceType;
    }

    public String getUserEquation() {
        return userEquation;
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    public String getCanonicalEquation() {
        return canonicalEquation;
    }
}
