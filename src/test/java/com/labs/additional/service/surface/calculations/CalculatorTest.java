package com.labs.additional.service.surface.calculations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        List<Double> coefficientsA = List.of(3d, 0d, 0d, -1d, -2d, 2.5, 0d, 0d, 0d, 0d);
        calculator = new Calculator(coefficientsA);
    }

    @Test
    void testCalcValues() {
        ValuesStorage expected = new ValuesStorage(
                3d, -4d, 0d, 25,
                -9.25, -14.75,
                -1, 4, 0);

        assertEquals(expected, calculator.calculateValues());
    }
}