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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurfaceValues that = (SurfaceValues) o;

        if (Double.compare(that.I1, I1) != 0) return false;
        if (Double.compare(that.I2, I2) != 0) return false;
        if (Double.compare(that.I3, I3) != 0) return false;
        if (Double.compare(that.I4, I4) != 0) return false;
        if (Double.compare(that.K2, K2) != 0) return false;
        if (Double.compare(that.K3, K3) != 0) return false;
        return cubicRoots.equals(that.cubicRoots);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(I1);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(I2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(I3);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(I4);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(K2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(K3);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + cubicRoots.hashCode();
        return result;
    }
}
