package com.labs.additional.service.surface.calculations;

public class EquationCoefficients {
    private final double a11, a12, a13, a14, a22, a23, a24, a33, a34, a44;

    public EquationCoefficients(double a11, double a12, double a13, double a14, double a22,
                                double a23, double a24, double a33, double a34, double a44) {
        this.a11 = a11;
        this.a12 = a12;
        this.a13 = a13;
        this.a14 = a14;
        this.a22 = a22;
        this.a23 = a23;
        this.a24 = a24;
        this.a33 = a33;
        this.a34 = a34;
        this.a44 = a44;
    }

    public double getA11() {
        return a11;
    }

    public double getA12() {
        return a12;
    }

    public double getA13() {
        return a13;
    }

    public double getA14() {
        return a14;
    }

    public double getA22() {
        return a22;
    }

    public double getA23() {
        return a23;
    }

    public double getA24() {
        return a24;
    }

    public double getA33() {
        return a33;
    }

    public double getA34() {
        return a34;
    }

    public double getA44() {
        return a44;
    }
}
