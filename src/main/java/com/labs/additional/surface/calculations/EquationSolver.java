package com.labs.additional.surface.calculations;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EquationSolver {

    static public List<Double> cubicEquation(double d, double a, double b, double c) {

        List<Double> result = new LinkedList<>();
        double x1, x2, x3;

        a /= d;
        b /= d;
        c /= d;

        double p = b - (a*a) / 3;
        double q = (2 * (a*a*a) / 27) - (a * b / 3) + c;
        double D = (p*p*p / 27) + (q*q / 4);
        D = Math.round(D * 10000.0) / 10000.0;

        if (D > 0) {
            x1 = Math.cbrt(-q / 2 + Math.sqrt(D)) + Math.cbrt(-q / 2 - Math.sqrt(D)) - a / 3;

            x1 = Math.round(x1 * 100);
            x1 /= 100;
            if (x1 != 0) {
                result.add(x1);
            }
        }
        else if (D == 0) {
            x1 = -2 * Math.cbrt(q / 2) - a / 3;
            x2 = Math.cbrt(q / 2) - a / 3;

            x1 = Math.round(x1 * 100);
            x1 /= 100;
            x2 = Math.round(x2 * 100);
            x2 /= 100;

            if (x1 != 0) {
                result.add(x1);
            }
            // We have 2 same roots
            if (x2 != 0) {
                result.add(x2);
                result.add(x2);
            }
        }
        else {
            x1 = (2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.sin((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))))) - (a / 3.0);
            x2 = (-2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.sin((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))) + (Math.PI / 3))) - (a / 3.0);
            x3 = (2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.cos((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))) + (Math.PI / 6))) - (a / 3.0);


            x1 = Math.round(x1 * 100);
            x1 /= 100;
            x2 = Math.round(x2 * 100);
            x2 /= 100;
            x3 = Math.round(x3 * 100);
            x3 /= 100;

            if (x1 != 0) {
                result.add(x1);
            }
            if (x2 != 0) {
                result.add(x2);
            }
            if (x3 != 0) {
                result.add(x3);
            }
        }

        return result;
    }
}
