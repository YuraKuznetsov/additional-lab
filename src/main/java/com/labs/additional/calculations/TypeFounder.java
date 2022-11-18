package com.labs.additional.calculations;

import java.util.List;
import java.util.NoSuchElementException;

public class TypeFounder {
    private Object request;
    private double a11, a12, a13, a14, a22, a23, a24, a33, a34, a44;
    private double[][] matrix3 = new double[3][3], matrix4 = new double[4][4];
    private double I1, I2, I3, I4, K2, K3;
    private double t1, t2, t3;  // cubic roots
    private String userEquation = "", myType, type;

    public TypeFounder(Object request) {
        this.request = request;
        setCoefficients();
        setMatrices();
        defineI();
        defineK();
        solveCubic();
        findUserEquation();
        findMyType();
        findType();
    }

    private void setCoefficients() {

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

    private void solveCubic() {
        List<Double> cubicRoots = Equation.cubicEquation(1, -I1, I2, -I3);
        // Delete zero roots
        for (int i = 0; i < cubicRoots.size(); i++) {
            if (cubicRoots.get(i) == 0.0) {
                cubicRoots.remove(i);
                i--;
            }
        }
        t1 = cubicRoots.get(0);
        if (cubicRoots.size() == 2) {
            t2 = cubicRoots.get(1);
        }
        if (cubicRoots.size() == 3) {
            t2 = cubicRoots.get(1);
            t3 = cubicRoots.get(2);
        }
    }

    private void findUserEquation() {
        userEquation += "1";
        System.out.println(userEquation);
    }

    private void findMyType() {
        if (I3 != 0) {
            // Скрізь квадрати
            myType = "full square";
        }
        // Циліндри, без змінної z
        if (I3 == 0 && I4 == 0 && I2 != 0) {
            myType = "cylinder";
        }

        // Параболоїди, просто z без квадрату
        if (I3 == 0 && I2 !=0 && I4 != 0) {
            myType = "paraboloid";
        }

        // Параболічний циліндр, без z та просто y без квадрату
        if (I2 == 0 && I3 == 0 && I4 == 0 && I1 != 0 && K3 != 0) {
            myType = "paraboloid cylinder";
        }
    }

    private void findType() {

    }

    public double getI1() {
        return I1;
    }

    public double getI2() {
        return I2;
    }

    public double getI3() {
        return I3;
    }

    public double getI4() {
        return I4;
    }

    public double getK2() {
        return K2;
    }

    public double getK3() {
        return K3;
    }

    public String getCanonicalEquation() {
        String canonicalEquation;
        double coefficient;
        double rightPart;
        switch (myType) {
            case "full square":
                coefficient = I4/I3;
                rightPart = coefficient != 0 ? -coefficient/coefficient : 0;
                canonicalEquation = t1/coefficient + "x^2 + " + t2/coefficient + "y^2 + " + t3/coefficient + "z^2 = " + rightPart;
                break;
            case "cylinder":
                coefficient = K3/I2;
                rightPart = coefficient != 0 ? -coefficient/coefficient : 0;
                canonicalEquation = t1/coefficient + "x^2 + " + t2/coefficient + "y^2 = " + rightPart;
                break;
            case "paraboloid":
                canonicalEquation = t1 + "x^2 + " + t2 + "y^2 + " + 2 * Math.sqrt(-I4/I2) + "z = 0";
                break;
            case "paraboloid cylinder":
                canonicalEquation = t1 + "x^2 + " + 2 * Math.sqrt(-K3/I1) + "y = 0";
                break;
            default:
                throw new NoSuchElementException("Щось не то...");
        }
        return canonicalEquation;
    }


    public String getType() {
        return type;
    }

}
