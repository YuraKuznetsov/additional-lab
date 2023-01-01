package com.labs.additional.service.surface.calculation;

import com.labs.additional.excepiton.MinorIsNotExistException;

import java.util.Arrays;

public class Matrix {
    private final double[][] matrix;
    private final int dimension;

    public Matrix(double[][] matrix) {
        this.dimension = matrix[0].length;
        this.matrix = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public double getDeterminant() {
        if (dimension == 1) {
            return matrix[0][0];
        }

        float determinant = 0;
        int sign = 1;

        for (int j = 0; j < dimension; j++) {
            Matrix minor = getMinor(0, j);
            determinant += sign * matrix[0][j] * minor.getDeterminant();
            sign = -sign;
        }

        return determinant;
    }

    public Matrix getMinor(int elRow, int elCol) {
        if (dimension < 2) {
            throw new MinorIsNotExistException("Can't generate minor of matrix which dimension less than 2");
        }

        final int minorDimension = dimension - 1;

        double[][] minor = new double[minorDimension][minorDimension];

        int currentRow = 0, currentCol = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i != elRow && j != elCol) {
                    minor[currentRow][currentCol++] = matrix[i][j];

                    if (currentCol == dimension - 1) {
                        currentCol = 0;
                        currentRow++;
                    }
                }
            }
        }

        return new Matrix(minor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix1 = (Matrix) o;

        return Arrays.deepEquals(matrix, matrix1.matrix);
    }
}
