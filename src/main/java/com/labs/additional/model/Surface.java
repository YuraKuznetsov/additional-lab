package com.labs.additional.model;

public class Surface {
    private final String equation;
    private final String type;
    private final String canonicalEquation;

    public Surface(String equation, String  type, String canonicalEquation) {
        this.equation = equation;
        this.type = type;
        this.canonicalEquation = canonicalEquation;
    }

    public String getEquation() {
        return equation;
    }

    public String getType() {
        return type;
    }

    public String getCanonicalEquation() {
        return canonicalEquation;
    }
}
