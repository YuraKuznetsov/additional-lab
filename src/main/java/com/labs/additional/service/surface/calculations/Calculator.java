package com.labs.additional.service.surface.calculations;

import java.util.List;
import java.util.Map;

public class Calculator {
    private final double a11, a12, a13, a14, a22, a23, a24, a33, a34, a44;
    private double I1, I2, I3, I4, K2, K3, lambda1, lambda2, lambda3;

    public Calculator(List<Double> coefficientsA) {
        this.a11 = coefficientsA.get(0);
        this.a12 = coefficientsA.get(1);
        this.a13 = coefficientsA.get(2);
        this.a14 = coefficientsA.get(3);
        this.a22 = coefficientsA.get(4);
        this.a23 = coefficientsA.get(5);
        this.a24 = coefficientsA.get(6);
        this.a33 = coefficientsA.get(7);
        this.a34 = coefficientsA.get(8);
        this.a44 = coefficientsA.get(9);
    }

    public ValuesStorage calculateValues() {
        calculateI();
        calculateK();
        calculateLambdas();

        return new ValuesStorage(I1, I2, I3, I4, K2, K3, lambda1, lambda2, lambda3);
    }


    private void calculateI() {
        Matrix matrix4by4 = getMatrix4by4();
        Matrix matrix3by3 = matrix4by4.getMinor(3, 3);

        I1 = a11 + a22 + a33;
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
        CubicEquationSolver cubicEquationSolver = new CubicEquationSolver();
        List<Double> cubicRoots = cubicEquationSolver.solve(1, -I1, I2, -I3);

        if (cubicRoots.size() >= 1) {
            lambda1 = cubicRoots.get(0);
        }
        if (cubicRoots.size() >= 2) {
            lambda2 = cubicRoots.get(1);
        }
        if (cubicRoots.size() == 3) {
            lambda3 = cubicRoots.get(2);
        }
    }

    private Matrix getMatrix4by4() {
        double[][] matrixAsArray = new double[][] {
                {a11, a12, a13, a14},
                {a12, a22, a23, a24},
                {a13, a23, a33, a34},
                {a14, a24, a34, a44}};

        return new Matrix(matrixAsArray);
    }
}
