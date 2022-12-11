package com.labs.additional.model;

import com.labs.additional.surface.type.SurfaceType;

public class Surface {
    private final String equation;
    private final SurfaceType type;
    private final String canonicalEquation;

    public Surface(String equation, SurfaceType type, String canonicalEquation) {
        this.equation = equation;
        this.type = type;
        this.canonicalEquation = canonicalEquation;
    }

    public String getEquation() {
        return equation;
    }

    public SurfaceType getType() {
        return type;
    }

    public String getCanonicalEquation() {
        return canonicalEquation;
    }
}
