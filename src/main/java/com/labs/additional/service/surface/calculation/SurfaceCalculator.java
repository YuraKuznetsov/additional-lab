package com.labs.additional.service.surface.calculation;

import com.labs.additional.model.CoefficientsA;
import com.labs.additional.model.SurfaceValues;
import com.labs.additional.service.surface.calculation.equation.cubic.CubicEquation;
import com.labs.additional.service.surface.calculation.equation.cubic.CubicRoots;

public class SurfaceCalculator {
    private CoefficientsA coefficients;
    private double I1, I2, I3, I4, K2, K3;
    private CubicRoots lambdas;

    public SurfaceValues calculateSurfaceValues(CoefficientsA coefficientsA) {
        coefficients = coefficientsA;
        calculateI();
        calculateK();
        calculateLambdas();

        return new SurfaceValues(I1, I2, I3, I4, K2, K3, lambdas);
    }

    private void calculateI() {
        Matrix matrix4by4 = getMatrix4by4();
        Matrix matrix3by3 = matrix4by4.getMinor(3, 3);

        I1 = coefficients.getA11() + coefficients.getA22() + coefficients.getA33();
        I2 = matrix3by3.getMinor(0, 0).getDeterminant()
                + matrix3by3.getMinor(1, 1).getDeterminant()
                + matrix3by3.getMinor(2, 2).getDeterminant();
        I3 = matrix3by3.getDeterminant();
        I4 = matrix4by4.getDeterminant();
    }

    private void calculateK() {
        Matrix matrix4by4 = getMatrix4by4();

        K2 = matrix4by4.getMinor(1, 1).getMinor(0, 0).getDeterminant()
                +  matrix4by4.getMinor(2, 2).getMinor(0, 0).getDeterminant()
                +  matrix4by4.getMinor(2, 2).getMinor(1, 1).getDeterminant();

        K3 = matrix4by4.getMinor(0, 0).getDeterminant()
                + matrix4by4.getMinor(1, 1).getDeterminant()
                + matrix4by4.getMinor(2, 2).getDeterminant();
    }

    private void calculateLambdas() {
        CubicEquation cubicEquation = new CubicEquation();
        lambdas = cubicEquation.calculateCubicRoots(1, -I1, I2, -I3);
    }

    private Matrix getMatrix4by4() {
        double[][] matrixAsArray = new double[][] {
                {coefficients.getA11(), coefficients.getA12(), coefficients.getA13(), coefficients.getA14()},
                {coefficients.getA12(), coefficients.getA22(), coefficients.getA23(), coefficients.getA24()},
                {coefficients.getA13(), coefficients.getA23(), coefficients.getA33(), coefficients.getA34()},
                {coefficients.getA14(), coefficients.getA24(), coefficients.getA34(), coefficients.getA44()}};

        return new Matrix(matrixAsArray);
    }
}
