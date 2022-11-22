package com.labs.additional.surface.calculations;

public class Matrix {

    public static double getDeterminant(double[][] matrix) {
        final int dimension = matrix.length;

        if (dimension == 1) {
            return matrix[0][0];
        }

        float determinant = 0;
        int sign = 1;

        for (int j = 0; j < dimension; j++) {
            double[][] minor = Matrix.getMinor(matrix, 0, j);
            determinant += sign * matrix[0][j] * getDeterminant(minor);
            sign = -sign;
        }

        return determinant;
    }

    public static double[][] getMinor(double[][] matrix, int elRow, int elCol) {
        final int matrixDim = matrix.length;
        final int minorDim = matrixDim - 1;

        double[][] minor = new double[minorDim][matrixDim];

        int currentRow = 0, currentCol = 0;

        for (int i = 0; i < matrixDim; i++) {
            for (int j = 0; j < matrixDim; j++) {
                if (i != elRow && j != elCol) {
                    minor[currentRow][currentCol++] = matrix[i][j];

                    if (currentCol == matrixDim - 1) {
                        currentCol = 0;
                        currentRow++;
                    }
                }
            }
        }

        return minor;
    }
}
