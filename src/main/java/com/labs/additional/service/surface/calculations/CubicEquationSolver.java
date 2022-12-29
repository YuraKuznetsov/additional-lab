package com.labs.additional.service.surface.calculations;

import java.util.ArrayList;
import java.util.List;

public class CubicEquationSolver {
    private double d, a, b, c;
    private double p, q, D;
    private double x1, x2, x3;

    public List<Double> solve(double d, double a, double b, double c) {
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = c;

        divideEquationCoefficients();
        calculateP();
        calculateQ();
        calculateDeterminant();

        if (D > 0) {
            findRootWithPositiveDeterminant();
        } else if (D == 0d) {
            findRootsWithZeroDeterminant();
        } else {
            findRootsWithNegativeDeterminant();
        }

        return getSuitableRoots();
    }

    private void divideEquationCoefficients() {
        a /= d;
        b /= d;
        c /= d;
    }

    private void calculateP() {
        p = b - (a*a) / 3;
    }

    private void calculateQ() {
        q = (2 * (a*a*a) / 27) - (a * b / 3) + c;
    }

    private void calculateDeterminant() {
        D = round3((p*p*p / 27) + (q*q / 4));
    }


    private void findRootWithPositiveDeterminant() {
        x1 = Math.cbrt(-q / 2 + Math.sqrt(D)) + Math.cbrt(-q / 2 - Math.sqrt(D)) - a / 3;
    }

    private void findRootsWithZeroDeterminant() {
        x1 = -2 * Math.cbrt(q / 2) - a / 3;
        x2 = Math.cbrt(q / 2) - a / 3;
    }

    private void findRootsWithNegativeDeterminant() {
        x1 = (2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.sin((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))))) - (a / 3.0);
        x2 = (-2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.sin((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))) + (Math.PI / 3))) - (a / 3.0);
        x3 = (2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.cos((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))) + (Math.PI / 6))) - (a / 3.0);
    }

    private List<Double> getSuitableRoots() {
        List<Double> result = new ArrayList<>();

        if (isSuitableRoot(x1)) {
            result.add(x1);
        }
        if (isSuitableRoot(x2)) {
            result.add(x2);
        }
        if (isSuitableRoot(x3)) {
            result.add(x3);
        }

        return result;
    }

    private boolean isSuitableRoot(Double root) {
        return root != null && round3(root) != 0d;
    }

    private double round3(double value) {
        value = Math.round(value * 1000);
        return value / 1000;
    }
}
