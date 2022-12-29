package com.labs.additional.model;

import com.labs.additional.service.surface.type.SurfaceType;

import java.util.Map;

public class Surface {
    private final String userEquation;
    private final Map<String, Double> values;  // I and K
    private final String cubicEquation;
    private final String canonicalEquationExplain;
    private final String canonicalEquationFormula;
    private final String simpleCanonicalEquation;
    private final String canonicalEquation;
    private final SurfaceType surfaceType;
    private final String imgSrc;

    public Surface(String userEquation, Map<String, Double> values, String cubicEquation,
                   String canonicalEquationExplain, String canonicalEquationFormula, String simpleCanonicalEquation,
                   String canonicalEquation, SurfaceType surfaceType, String imgSrc) {

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

    public SurfaceType getSurfaceType() {
        return surfaceType;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
