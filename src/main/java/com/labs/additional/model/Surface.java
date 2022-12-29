package com.labs.additional.model;

import java.util.Map;

public class Surface {
    private final String userEquation;
    private final Map<String, Double> values;  // I and K
    private final String cubicEquation;
    private final String canonicalEquationExplain;
    private final String canonicalEquationFormula;
    private final String simpleCanonicalEquation;
    private final String canonicalEquation;
    private final String surfaceType;
    private final String imgSrc;

    public Surface(String userEquation, Map<String, Double> values, String cubicEquation,
                   String canonicalEquationExplain, String canonicalEquationFormula, String simpleCanonicalEquation,
                   String canonicalEquation, String surfaceType, String imgSrc) {

        this.userEquation = userEquation;
        this.values = values;
        this.cubicEquation = cubicEquation;
        this.canonicalEquationExplain = canonicalEquationExplain;
        this.canonicalEquationFormula = canonicalEquationFormula;
        this.simpleCanonicalEquation = simpleCanonicalEquation;
        this.canonicalEquation = canonicalEquation;
        this.surfaceType = surfaceType;
        this.imgSrc = imgSrc;
    }

    public String getUserEquation() {
        return userEquation;
    }

    public Map<String, Double> getValues() {
        return values;
    }

    public String getCubicEquation() {
        return cubicEquation;
    }

    public String getCanonicalEquationExplain() {
        return canonicalEquationExplain;
    }

    public String getCanonicalEquationFormula() {
        return canonicalEquationFormula;
    }

    public String getSimpleCanonicalEquation() {
        return simpleCanonicalEquation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Surface surface = (Surface) o;

        return userEquation.equals(surface.userEquation);
    }

    @Override
    public int hashCode() {
        return userEquation.hashCode();
    }
}
