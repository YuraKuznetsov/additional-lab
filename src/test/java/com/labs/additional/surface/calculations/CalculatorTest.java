package com.labs.additional.surface.calculations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void roundInt() {
        double expected = 1.;
        double actual = Calculator.round(1.);
        assertEquals(expected, actual);
    }

    @Test
    void round4decimal() {
        double expected = 1.23;
        double actual = Calculator.round(1.2342);
        assertEquals(expected, actual);
    }

    @Test
    void round6decimal() {
        double expected = 1.23;
        double actual = Calculator.round(1.227682);
        assertEquals(expected, actual);
    }
}