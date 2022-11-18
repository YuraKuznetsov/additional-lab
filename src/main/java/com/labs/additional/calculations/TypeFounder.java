package com.labs.additional.calculations;

import java.util.*;

public class TypeFounder {
    private final Map<String, String> userCoefficients;
    private double a11, a12, a13, a14, a22, a23, a24, a33, a34, a44;
    private double[][] matrix3 = new double[3][3], matrix4 = new double[4][4];
    private double I1, I2, I3, I4, K2, K3;
    private double lambda1, lambda2, lambda3;
    private String userEquation = "", myType, type;

    public TypeFounder(Map<String, String> userCoefficients) {
        this.userCoefficients = userCoefficients;
    }

    public void makeCalculations() {
        setCoefficients();
        setMatrices();
        defineI();
        defineK();
        calcLambdas();
    }

    private void setCoefficients() {
        a11 = stringToDouble(userCoefficients.get("a11"));
        a12 = stringToDouble(userCoefficients.get("a12")) / 2;
        a13 = stringToDouble(userCoefficients.get("a13")) / 2;
        a14 = stringToDouble(userCoefficients.get("a14")) / 2;
        a22 = stringToDouble(userCoefficients.get("a22"));
        a23 = stringToDouble(userCoefficients.get("a23")) / 2;
        a24 = stringToDouble(userCoefficients.get("a24")) / 2;
        a33 = stringToDouble(userCoefficients.get("a33"));
        a34 = stringToDouble(userCoefficients.get("a34")) / 2;
        a44 = stringToDouble(userCoefficients.get("a44"));
    }

    private double stringToDouble(String string) {
        if (string.isEmpty()) return 0;
        return Double.parseDouble(string);
    }

    private void setMatrices() {
        matrix4 = new double[][] {
                {a11, a12, a13, a14},
                {a12, a22, a23, a24},
                {a13, a23, a33, a34},
                {a14, a24, a34, a44}};

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
        K2 = Matrix.getDeterminant(new double[][] {{a11, a14}, {a14, a44}})
                + Matrix.getDeterminant(new double[][] {{a22, a24}, {a24, a44}})
                + Matrix.getDeterminant(new double[][] {{a33, a34}, {a34, a44}});

        K3 = Matrix.getDeterminant(Matrix.getMinor(matrix4, 2, 2))
                + Matrix.getDeterminant(Matrix.getMinor(matrix4, 0, 0))
                + Matrix.getDeterminant(Matrix.getMinor(matrix4, 1, 1));
    }

    private void calcLambdas() {
        List<Double> cubicRoots = Equation.cubicEquation(1, -I1, I2, -I3);
        removeZeroElements(cubicRoots);

        lambda1 = cubicRoots.get(0);
        if (cubicRoots.size() == 2) {
            lambda2 = cubicRoots.get(1);
        }
        if (cubicRoots.size() == 3) {
            lambda2 = cubicRoots.get(1);
            lambda3 = cubicRoots.get(2);
        }
    }

    private void removeZeroElements(List<Double> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0.0) {
                list.remove(i);
                i--;
            }
        }
    }

    public String getCanonicalEquation() {

        double coefficient;
        double rightPart;
        switch (getFamilyType()) {
            case "full square":
                coefficient = I4/I3;
                rightPart = coefficient != 0 ? -coefficient/coefficient : 0;
                return lambda1 /coefficient + "x^2 + " + lambda2 /coefficient + "y^2 + " + lambda3 /coefficient + "z^2 = " + rightPart;
            case "cylinder":
                coefficient = K3/I2;
                rightPart = coefficient != 0 ? -coefficient/coefficient : 0;
                return lambda1 /coefficient + "x^2 + " + lambda2 /coefficient + "y^2 = " + rightPart;
            case "paraboloid":
                return lambda1 + "x^2 + " + lambda2 + "y^2 + " + 2 * Math.sqrt(-I4/I2) + "z = 0";
            case "paraboloid cylinder":
                return lambda1 + "x^2 + " + 2 * Math.sqrt(-K3/I1) + "y = 0";
            default:
                throw new NoSuchElementException("Щось не то...");
        }
    }

    private String getFamilyType() {
        // Скрізь квадрати
        if (I3 != 0) {
            return "full square";
        }
        // Циліндри, без змінної z
        if (I3 == 0 && I4 == 0 && I2 != 0) {
            return "cylinder";
        }

        // Параболоїди, просто z без квадрату
        if (I3 == 0 && I2 !=0 && I4 != 0) {
            return "paraboloid";
        }

        // Параболічний циліндр, без z та просто y без квадрату
        if (I1 != 0 && I2 == 0 && I3 == 0 && I4 == 0 && K3 != 0) {
            return  "paraboloid cylinder";
        }

        return "Not a second-order surface";
    }

    private String findParaboloid() {
        if (Math.signum(lambda1) != Math.signum(lambda2)) return "Гіперболічний параболоїд";
        if (lambda1 != lambda2) return "Еліптичний параболоїд";
        return "Коловий параболоїд";
    }

    private String findCylinder() {
        if (Math.signum(lambda1) != Math.signum(lambda2)) return "Гіперболічний циліндр";
        if (lambda1 != lambda2) return "Еліптичний циліндр";
        return "Коловий циліндр";
    }

    private String findFullSquare() {
        double sign1 = Math.signum(lambda1), sign2 = Math.signum(lambda2), sign3 = Math.signum(lambda3);

        if (sign1 == sign2 && sign1 == sign3) {
            return "Еліпсоїд";
        }

        if (I4 == 0) {
            return "Конус";
        }

        return "Гіперболоїд";

    }



    public String getType() {

        switch (getFamilyType()) {
            case "paraboloid cylinder":
                return "Параболічний циліндр";
            case "paraboloid":
                return findParaboloid();
            case "cylinder":
                return findCylinder();
            case "full square":
                return findFullSquare();
        }
        return "Площина";
    }
}
