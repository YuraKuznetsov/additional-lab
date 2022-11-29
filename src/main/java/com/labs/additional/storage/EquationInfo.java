package com.labs.additional.storage;

public class EquationInfo {
    private final String userEquation;
    private final String surfaceType;

    public EquationInfo(String userEquation, String surfaceType) {
        this.userEquation = userEquation;
        this.surfaceType = surfaceType;
    }

    public String getUserEquation() {
        return userEquation;
    }

    public String getSurfaceType() {
        return surfaceType;
    }
}
