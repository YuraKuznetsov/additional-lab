package com.labs.additional.service.surface;

import com.labs.additional.service.surface.calculations.Calculator;
import com.labs.additional.service.surface.type.WideSurfaceType;

import java.util.Map;

public class EquationGenerator {
    private static final Map<String, String> coefficientMeaning = Map.of(
            "a11", "x^2", "a22", "y^2", "a33", "z^2",
            "a12", "xy", "a13", "xz", "a14", "x",
            "a23", "yz", "a24", "y",
            "a34", "z", "a44", ""
    );
    public static String getCanonical(Map<String, Double> values, WideSurfaceType wideSurfaceType) {
        double I1 = values.get("I1"), I2 = values.get("I2"), I3 = values.get("I3"), I4 = values.get("I4"),
                K3 = values.get("K3"),
                lambda1 = values.get("lambda1"), lambda2 = values.get("lambda2"), lambda3 = values.get("lambda3");

        double coefficient;

        switch (wideSurfaceType) {
            case FULL_SQUARE:
                coefficient = I4/I3;

                // Циліндр
                if (coefficient == 0.0) {
                    return lambda1 + "x² + " + lambda2 + "y² + " + lambda3  + "z² = 0";
                }

                return Calculator.round3(lambda1 / -coefficient) + "x² + "
                        + Calculator.round3(lambda2 / -coefficient) + "y² + "
                        + Calculator.round3(lambda3 / -coefficient) + "z² = 1";

            case CYLINDER:
                coefficient = K3/I2;
                return Calculator.round3(lambda1 / -coefficient) + "x² + "
                        + Calculator.round3(lambda2 / -coefficient) + "y² = 1";

            case PARABOLOID:
                coefficient = Calculator.round3(2 * Math.sqrt(-I4/I2));
                return lambda1 + "x² + " + lambda2 + "y² + " + coefficient  + "z = 0";

            case PARABOLIC_CYLINDER:
                coefficient = Calculator.round3(2 * Math.sqrt(-K3/I1));
                return lambda1 + "x² + " + coefficient + "y = 0";
        }

        return "Не підтримується";
    }

    public static String getUserEquation(Map<String, String> request) {
        StringBuilder equation = new StringBuilder();

        for (String key : request.keySet()) {
            String value = request.get(key);
            if (value.isEmpty() || value.equals("0")) continue;
            equation.append(value).append(coefficientMeaning.get(key));
            equation.append(" + ");
        }

        int equationLength = equation.length();
        if (equationLength == 0) return "";

        equation.replace(equationLength - 2, equationLength, "");
        equation.append("= 0");

        return equation.toString();
    }

    public static String getCubicEquation(Map<String, Double> values) {
        double I1 = values.get("I1"), I2 = values.get("I2"), I3 = values.get("I3");
        return "λ³ + " + -I1 + "λ² + " + I2 + "λ + " + -I3 + " = 0";
    }
}