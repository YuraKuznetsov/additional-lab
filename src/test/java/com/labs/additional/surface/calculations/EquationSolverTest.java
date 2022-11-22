package com.labs.additional.surface.calculations;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


class EquationSolverTest {

    @Test
    void cubicEquation() {
        List<Double> expected1 = List.of();
        List<Double> result1 = EquationSolver.cubicEquation(1.0, 0.0, 0.0, 0.0);
        assertEquals(expected1, result1);

        List<Double> expected2 = List.of(-20.0);
        List<Double> result2 = EquationSolver.cubicEquation(1.0, 20.0, 0.0, 0.0);
        assertEquals(expected2, result2);

        List<Double> expected3 = List.of(-3.56, 0.56);
        List<Double> result3 = EquationSolver.cubicEquation(1.0, 3.0, -2.0, 0.0);
        assertEquals(expected3, result3);

        List<Double> expected4 = List.of(5.0, 3.0, 3.0);
        List<Double> result4 = EquationSolver.cubicEquation(1.0, -11.0, 39.0, -45);
        assertEquals(expected4, result4);
    }
}