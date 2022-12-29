package com.labs.additional.service.surface;

import com.labs.additional.service.surface.type.WideSurfaceType;

import java.util.Map;

public class EquationGenerator {
    private static final Map<String, String> coefficientMeaning = Map.of(
            "a11", "x²", "a22", "y²", "a33", "z²",
            "a12", "xy", "a13", "xz", "a14", "x",
            "a23", "yz", "a24", "y",
            "a34", "z", "a44", ""
    );

    public static String generateUserEquation(Map<String, String> request) {
        StringBuilder userEquation = new StringBuilder();

        for (String key : request.keySet()) {
            String value = request.get(key);
            if (!isValueExist(value)) continue;

            String meaning = coefficientMeaning.get(key);
            String blockOfEquation = generateBlockOfEquation(value, meaning);
            userEquation.append(blockOfEquation);
        }

        if (!userEquation.isEmpty()) {
            deleteSpaceAtBeginning(userEquation);
            addRightPart(userEquation);
        }

        return userEquation.toString();
    }

    private static boolean isValueExist(String value) {
        return !value.isEmpty() && !value.equals("0");
    }

    private static String generateBlockOfEquation(String coefficientValue, String coefficientValueMeaning) {
        String sign = coefficientValue.contains("-") ? "-" : "+";
        String absoluteValue = coefficientValue.replace("-", "");
        return String.format(" %s %s%s", sign, absoluteValue, coefficientValueMeaning);
    }

    private static void deleteSpaceAtBeginning(StringBuilder equation) {
        if (isFirstValueNegative(equation)) {
            equation.delete(0, 1);
        } else {
            equation.delete(0, 3);
        }
    }

    private static void addRightPart(StringBuilder equation) {
        equation.append(" = 0");
    }

    private static boolean isFirstValueNegative(StringBuilder equation) {
        return equation.substring(0, 3).equals(" - ");
    }

    public static String getCubicEquation(Map<String, Double> values) {
        double I1 = values.get("I1"), I2 = values.get("I2"), I3 = values.get("I3");
        return "λ³ + " + -I1 + "λ² + " + I2 + "λ + " + -I3 + " = 0";
    }

    public static String getSimpleCanonical(Map<String, Double> values, WideSurfaceType wideSurfaceType) {
        double I1 = values.get("I1"), I2 = values.get("I2"), I3 = values.get("I3"), I4 = values.get("I4"),
                K3 = values.get("K3"),
                lambda1 = values.get("lambda1"), lambda2 = values.get("lambda2"), lambda3 = values.get("lambda3");

        double coefficient;

        switch (wideSurfaceType) {
            case FULL_SQUARE -> {
                coefficient = round2(I4 / I3);

                return lambda1 + "x² + " + lambda2 + "y² + " + lambda3 + "z² + " + coefficient + " = 0";
            }
            case CYLINDER -> {
                coefficient = round2(K3 / I2);
                return lambda1 + "x² + " + lambda2 + "y² + " + coefficient + " = 0";
            }
            case PARABOLOID -> {
                coefficient = round3(2 * Math.sqrt(-I4 / I2));
                return lambda1 + "x² + " + lambda2 + "y² + " + coefficient + "z = 0";
            }
            case PARABOLIC_CYLINDER -> {
                coefficient = round3(2 * Math.sqrt(-K3 / I1));
                return lambda1 + "x² + " + coefficient + "y = 0";
            }
            default -> {
                return "Не підтримується";
            }
        }
    }

    public static String getCanonical(Map<String, Double> values, WideSurfaceType wideSurfaceType) {
        double I1 = values.get("I1"), I2 = values.get("I2"), I3 = values.get("I3"), I4 = values.get("I4"),
                K3 = values.get("K3"),
                lambda1 = values.get("lambda1"), lambda2 = values.get("lambda2"), lambda3 = values.get("lambda3");

        double coefficient;

        switch (wideSurfaceType) {
            case FULL_SQUARE -> {
                coefficient = I4 / I3;

                if (coefficient == 0.0) {
                    return lambda1 + "x² + " + lambda2 + "y² + " + lambda3 + "z² = 0";
                }
                return round3(lambda1 / -coefficient) + "x² + "
                        + round3(lambda2 / -coefficient) + "y² + "
                        + round3(lambda3 / -coefficient) + "z² = 1";
            }
            case CYLINDER -> {
                coefficient = K3 / I2;
                return round3(lambda1 / -coefficient) + "x² + "
                        + round3(lambda2 / -coefficient) + "y² = 1";
            }
            case PARABOLOID -> {
                coefficient = round3(2 * Math.sqrt(-I4 / I2));
                return lambda1 + "x² + " + lambda2 + "y² + " + coefficient + "z = 0";
            }
            case PARABOLIC_CYLINDER -> {
                coefficient = round3(2 * Math.sqrt(-K3 / I1));
                return lambda1 + "x² + " + coefficient + "y = 0";
            }
            default -> {
                return "Не підтримується";
            }
        }
    }

    private static double round2(double value) {
        value = Math.round(value * 100);
        return value / 100;
    }

    private static double round3(double value) {
        value = Math.round(value * 1000);
        return value / 1000;
    }
}
