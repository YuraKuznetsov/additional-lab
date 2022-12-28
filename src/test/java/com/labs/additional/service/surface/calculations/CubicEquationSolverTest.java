package com.labs.additional.service.surface.calculations;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CubicEquationSolverTest {
    CubicEquationSolver cubicEquationSolver;

    @BeforeEach
    void setUp() {
        cubicEquationSolver = new CubicEquationSolver();
    }

    @Test
    void isEmpty_whenAllCubicCoefficientsAreZero() {
        List<Double> expected = List.of();
        List<Double> result = cubicEquationSolver.solve(1.0, 0.0, 0.0, 0.0);
        assertEquals(expected, result);
    }

    @Test
    void oneCubicNotZeroRoot_whenGivenOnlyNotZeroI1() {
        for (double I1 = -10; I1 <= 10; I1 += .5) {
            if (I1 == 0) continue;
            List<Double> expected = List.of(-I1);
            List<Double> result = cubicEquationSolver.solve(1.0, I1, 0.0, 0.0);

            assertEquals(expected, result);
        }
    }

    @Test
    void threeCubicNotZeroRoots_whenNotZeroI3() {
        final double I3 = 5.1;
        final int expectedSize = 3;
        List<Double> resul = cubicEquationSolver.solve(1.0, 13.4, -22.5, I3);
        assertEquals(expectedSize, resul.size());
    }

    @Test
    void lessThanThreeCubicRoots_whenZeroI3() {
        final double I3 = 0;
        List<Double> resul = cubicEquationSolver.solve(1.0, 13.4, -22.5, I3);
        System.out.println(resul);
        assertTrue(resul.size() < 3);
    }

}