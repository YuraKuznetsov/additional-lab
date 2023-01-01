package com.labs.additional.service.surface.calculation.equation.cubic;

import com.labs.additional.excepiton.IsNotCubicEquationException;

public class CubicEquation {
    private double d, a, b, c;
    private double p, q, D;
    private CubicRoots cubicRoots;

    public CubicRoots calculateCubicRoots(double d, double a, double b, double c) {
        if (d == 0) throw new IsNotCubicEquationException("Parameter d must not be zero");
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = c;

        divideEquationCoefficients();
        calculateP();
        calculateQ();
        calculateDeterminant();
        findCubicRoots();

        return cubicRoots;
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
        D = (p*p*p / 27) + (q*q / 4);
    }

    private void findCubicRoots() {
        if (round3(D) > 0) {
            findRootWithPositiveDeterminant();
        } else if (round3(D) == 0d) {
            findRootsWithZeroDeterminant();
        } else {
            findRootsWithNegativeDeterminant();
        }
    }

    private double round3(double value) {
        value = Math.round(value * 1000);
        return value / 1000;
    }

    private void findRootWithPositiveDeterminant() {
        double root1 = Math.cbrt(-q / 2 + Math.sqrt(D)) + Math.cbrt(-q / 2 - Math.sqrt(D)) - a / 3;

        cubicRoots = new CubicRoots(root1);
    }

    private void findRootsWithZeroDeterminant() {
        double root1 = -2 * Math.cbrt(q / 2) - a / 3;
        double root2 = Math.cbrt(q / 2) - a / 3;

        cubicRoots = new CubicRoots(root1, root2, root2);
    }

    private void findRootsWithNegativeDeterminant() {
        double root1 = (2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.sin((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))))) - (a / 3.0);
        double root2 = (-2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.sin((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))) + (Math.PI / 3))) - (a / 3.0);
        double root3 = (2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.cos((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))) + (Math.PI / 6))) - (a / 3.0);

        cubicRoots = new CubicRoots(root1, root2, root3);
    }
}
