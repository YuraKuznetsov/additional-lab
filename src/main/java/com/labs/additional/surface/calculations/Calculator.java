package com.labs.additional.surface.calculations;

import java.util.List;
import java.util.Map;

public class Calculator {
    private final Map<String, Double> coefficients;
    private double[][] matrix3 = new double[3][3], matrix4 = new double[4][4];
    private double I1, I2, I3, I4, K2, K3, lambda1, lambda2, lambda3;

    public Calculator(Map<String, Double> coefficients) {
        this.coefficients = coefficients;
    }

    public Map<String, Double> calcValues() {
        fillMatrices();
        defineI();
        defineK();
        calcLambdas();
        return Map.of(
                "I1", round(I1), "I2", round(I2), "I3", round(I3), "I4", round(I4),
                "K2", round(K2), "K3", round(K3),
                "lambda1", round(lambda1), "lambda2", round(lambda2), "lambda3", round(lambda3));
    }

    private void fillMatrices() {
        matrix4 = new double[][] {
                {coefficients.get("a11"), coefficients.get("a12"), coefficients.get("a13"), coefficients.get("a14")},
                {coefficients.get("a12"), coefficients.get("a22"), coefficients.get("a23"), coefficients.get("a24")},
                {coefficients.get("a13"), coefficients.get("a23"), coefficients.get("a33"), coefficients.get("a34")},
                {coefficients.get("a14"), coefficients.get("a24"), coefficients.get("a34"), coefficients.get("a44")}
        };
        matrix3 = Matrix.getMinor(matrix4, 3, 3);
    }

    private void defineI() {
        I1 = matrix4[0][0] + matrix4[1][1] + matrix4[2][2];
        I2 = Matrix.getDeterminant(Matrix.getMinor(matrix3, 2, 2))
                + Matrix.getDeterminant(Matrix.getMinor(matrix3, 0, 0))
                + Matrix.getDeterminant(Matrix.getMinor(matrix3, 1, 1));
        I3 = Matrix.getDeterminant(matrix3);
        I4 = Matrix.getDeterminant(matrix4);
    }

    private void defineK() {
        K2 = Matrix.getDeterminant(new double[][] {
                    {coefficients.get("a11"), coefficients.get("a14")},
                    {coefficients.get("a14"), coefficients.get("a44")}})

                + Matrix.getDeterminant(new double[][] {
                        {coefficients.get("a22"), coefficients.get("a24")},
                        {coefficients.get("a24"), coefficients.get("a44")}})

                + Matrix.getDeterminant(new double[][] {
                        {coefficients.get("a33"), coefficients.get("a34")},
                        {coefficients.get("a34"), coefficients.get("a44")}});

        K3 = Matrix.getDeterminant(Matrix.getMinor(matrix4, 2, 2))
                + Matrix.getDeterminant(Matrix.getMinor(matrix4, 0, 0))
                + Matrix.getDeterminant(Matrix.getMinor(matrix4, 1, 1));
    }

    private void calcLambdas() {
        List<Double> cubicRoots = EquationSolver.cubicEquation(1, -I1, I2, -I3);

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

    public static double round(double value) {
        value = Math.round(value * 100);
        value /= 100;
        return value;
    }
}
