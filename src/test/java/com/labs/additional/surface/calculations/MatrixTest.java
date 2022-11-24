package com.labs.additional.surface.calculations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    double[][] matrix;

    @Test
    void zeroDeterminant_whenMatrixIsEmpty() {
        matrix = new double[][] {};
        assertEquals(0.0, Matrix.getDeterminant(matrix));
    }

    @Test
    void determinant_whenMatrixLength1() {
        final double a = 3.87;
        matrix = new double[][] {{a}};
        assertEquals(a, Matrix.getDeterminant(matrix));
    }

    @Test
    void determinantMatrix3by3() {
        matrix = new double[][] {
                {1.3, 4.2, 3.},
                {10.4, 3.3, -32.2},
                {-21.32, 0., -9.2}
        };
        assertEquals(3456.77294921875, Matrix.getDeterminant(matrix));
    }
}