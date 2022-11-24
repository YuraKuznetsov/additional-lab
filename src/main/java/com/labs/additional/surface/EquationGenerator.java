package com.labs.additional.surface;

import com.labs.additional.surface.type.WideSurfaceType;

import java.util.Map;

public class EquationGenerator {
    private static final Map<String, String> coefficientMeaning = Map.of(
            "a11", "x²", "a22", "y²", "a33", "z²",
            "a12", "xy", "a13", "xz", "a14", "x",
            "a23", "yz", "a24", "y",
            "a34", "z", "a44", ""
    );
    public static String getCanonical(Map<String, Double> values, WideSurfaceType wideSurfaceType) {
        return "";
//        double coefficient;
//        double rightPart;
//        switch (wideSurfaceType) {
//            case "full square":
//                coefficient = I4/I3;
//                rightPart = coefficient != 0 ? -coefficient/coefficient : 0;
//                return lambda1 /coefficient + "x^2 + " + lambda2 /coefficient + "y^2 + " + lambda3 /coefficient + "z^2 = " + rightPart;
//            case "cylinder":
//                coefficient = K3/I2;
//                rightPart = coefficient != 0 ? -coefficient/coefficient : 0;
//                return lambda1 /coefficient + "x^2 + " + lambda2 /coefficient + "y^2 = " + rightPart;
//            case "paraboloid":
//                return lambda1 + "x^2 + " + lambda2 + "y^2 + " + 2 * Math.sqrt(-I4/I2) + "z = 0";
//            case "paraboloid cylinder":
//                return lambda1 + "x^2 + " + 2 * Math.sqrt(-K3/I1) + "y = 0";
//        }
    }

    public static String getUserEquation(Map<String, String> request) {
        StringBuilder equation = new StringBuilder();

        for (String key : request.keySet()) {
            String value = request.get(key);
            if (value.isEmpty()) continue;
            equation.append(value).append(coefficientMeaning.get(key));
            equation.append(" + ");
        }

        int equationLength = equation.length();
        equation.replace(equationLength - 2, equationLength, "");
        equation.append("= 0");

        return equation.toString();
    }
}
