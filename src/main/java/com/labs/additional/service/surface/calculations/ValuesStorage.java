package com.labs.additional.service.surface.calculations;

public class ValuesStorage {
    private final double I1, I2, I3, I4, K2, K3, lambda1, lambda2, lambda3;

    public ValuesStorage(double I1, double I2, double I3, double I4, double K2, double K3,
                         double lambda1, double lambda2, double lambda3) {
        this.I1 = I1;
        this.I2 = I2;
        this.I3 = I3;
        this.I4 = I4;
        this.K2 = K2;
        this.K3 = K3;
        this.lambda1 = lambda1;
        this.lambda2 = lambda2;
        this.lambda3 = lambda3;
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

    public double getLambda1() {
        return lambda1;
    }

    public double getLambda2() {
        return lambda2;
    }

    public double getLambda3() {
        return lambda3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValuesStorage that = (ValuesStorage) o;

        if (Double.compare(that.I1, I1) != 0) return false;
        if (Double.compare(that.I2, I2) != 0) return false;
        if (Double.compare(that.I3, I3) != 0) return false;
        if (Double.compare(that.I4, I4) != 0) return false;
        if (Double.compare(that.K2, K2) != 0) return false;
        if (Double.compare(that.K3, K3) != 0) return false;
        if (Double.compare(that.lambda1, lambda1) != 0) return false;
        if (Double.compare(that.lambda2, lambda2) != 0) return false;
        return Double.compare(that.lambda3, lambda3) == 0;
    }
}
