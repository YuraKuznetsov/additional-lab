package com.labs.additional.model;

public class CoefficientsA {
    private final double a11, a22, a33, a12, a13, a23, a14, a24, a34, a44;

    public CoefficientsA(double a11, double a12, double a13, double a14, double a22,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoefficientsA that = (CoefficientsA) o;

        if (Double.compare(that.a11, a11) != 0) return false;
        if (Double.compare(that.a22, a22) != 0) return false;
        if (Double.compare(that.a33, a33) != 0) return false;
        if (Double.compare(that.a12, a12) != 0) return false;
        if (Double.compare(that.a13, a13) != 0) return false;
        if (Double.compare(that.a23, a23) != 0) return false;
        if (Double.compare(that.a14, a14) != 0) return false;
        if (Double.compare(that.a24, a24) != 0) return false;
        if (Double.compare(that.a34, a34) != 0) return false;
        return Double.compare(that.a44, a44) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(a11);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(a22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(a33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(a12);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(a13);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(a23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(a14);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(a24);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(a34);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(a44);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
