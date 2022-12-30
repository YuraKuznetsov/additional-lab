package com.labs.additional.service.surface.calculations;

import com.labs.additional.excepiton.MinorIsNotExistException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    Matrix matrix;

    @Test
    void zeroDeterminant_whenMatrixIsEmpty() {
        double[][] emptyArray = new double[][] {{}};
        matrix = new Matrix(emptyArray);
        assertEquals(0.0, matrix.getDeterminant());
    }

    @Test
    void determinant_whenMatrixLength1() {
        double[][] matrixAsArray = new double[][] {{3.5}};
        matrix = new Matrix(matrixAsArray);
        assertEquals(3.5, matrix.getDeterminant());
    }

    @Test
    void determinantMatrix3by3() {
        double[][] matrixAsArray = new double[][] {
                {1.3, 4.2, 3.},
                {10.4, 3.3, -32.2},
                {-21.32, 0., -9.2}
        };
        matrix = new Matrix(matrixAsArray);
        assertEquals(3456.77294921875, matrix.getDeterminant());
    }

    @Test
    void getMinorOfEmptyMatrix() {
        double[][] emptyArray = new double[][] {{}};
        matrix = new Matrix(emptyArray);
        assertThrows(MinorIsNotExistException.class, () -> matrix.getMinor(0, 0));
    }

    @Test
    void MinorOfMatrix3by3() {
        double[][] matrixAsArray = new double[][] {
                {1.3, 4.2, 3.},
                {10.4, 3.3, -32.2},
                {-21.32, 0., -9.2}
        };
        double[][] minorAsArray = new double[][] {
                {1.3, 4.2},
                {10.4, 3.3},
        };

        matrix = new Matrix(matrixAsArray);
        Matrix expectedMinor = new Matrix(minorAsArray);
        Matrix actualMinor = matrix.getMinor(2, 2);

        assertEquals(expectedMinor, actualMinor);
    }
}