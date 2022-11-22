package com.labs.additional.surface;

import com.labs.additional.surface.type.SurfaceType;
import com.labs.additional.surface.type.WideSurfaceType;

import java.util.Map;
import java.util.NoSuchElementException;

public class EquationGenerator {
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
}
