package com.labs.additional.model;

import com.labs.additional.service.surface.calculation.equation.cubic.CubicRoots;

public class SurfaceValues {
    private final double I1, I2, I3, I4, K2, K3;
    private final CubicRoots cubicRoots;

    public SurfaceValues(double I1, double I2, double I3, double I4, double K2, double K3, CubicRoots cubicRoots) {
        this.I1 = I1;
        this.I2 = I2;
        this.I3 = I3;
        this.I4 = I4;
        this.K2 = K2;
        this.K3 = K3;
        this.cubicRoots = cubicRoots;
    }

    public double getI1() {
        return I1;
    }

    public double getI2() {
        return I2;
    }

    public double getI3() {
        return I3;
    }

    public double getI4() {
        return I4;
    }

    public double getK2() {
        return K2;
    }

    public double getK3() {
        return K3;
    }

    public CubicRoots getCubicRoots() {
        return cubicRoots;
    }
}
