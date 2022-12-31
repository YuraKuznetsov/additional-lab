package com.labs.additional.service.surface.calculations;

import com.labs.additional.excepiton.IsNotCubicEquationException;
import com.labs.additional.service.surface.calculations.equation.cubic.CubicEquation;
import com.labs.additional.service.surface.calculations.equation.cubic.CubicRoots;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CubicEquationTest {
    CubicEquation cubicEquation;

    @BeforeEach
    void setUp() {
        cubicEquation = new CubicEquation();
    }

    @Test
    void throwException_whenDIsZero() {
        assertThrows(IsNotCubicEquationException.class,
                     () -> cubicEquation.calculateCubicRoots(0, 10, -2, 14.3));
    }

    @Test
    void isEmpty_whenAllCubicCoefficientsAreZero() {
        CubicRoots cubicRoots = cubicEquation.calculateCubicRoots(1.0, 0.0, 0.0, 0.0);

        assertTrue(cubicRoots.getRoot1().isPresent());
        assertTrue(cubicRoots.getRoot2().isPresent());
        assertTrue(cubicRoots.getRoot3().isPresent());

        assertEquals(-0.0, cubicRoots.getRoot1().get());
        assertEquals(0, cubicRoots.getRoot2().get());
        assertEquals(0, cubicRoots.getRoot3().get());
    }

    @Test
    void twoZeroCubicRoot_whenGivenOnlyI1() {
        for (double I1 = -10; I1 <= 10; I1 += .5) {
            if (I1 == 0) continue;

            CubicRoots cubicRoots = cubicEquation.calculateCubicRoots(1.0, I1, 0.0, 0.0);

            assertTrue(cubicRoots.getRoot1().isPresent());
            assertTrue(cubicRoots.getRoot2().isPresent());
            assertTrue(cubicRoots.getRoot3().isPresent());

            assertEquals(-I1, cubicRoots.getRoot1().get());
            assertEquals(0, cubicRoots.getRoot2().get());
            assertEquals(0, cubicRoots.getRoot3().get());
        }
    }

    @Test
    void threeCubicRoots_whenGivenI3() {
        final double I3 = 5.1;
        CubicRoots cubicRoots = cubicEquation.calculateCubicRoots(1.0, 13.4, -22.5, I3);

        assertTrue(cubicRoots.getRoot1().isPresent());
        assertTrue(cubicRoots.getRoot2().isPresent());
        assertTrue(cubicRoots.getRoot3().isPresent());
    }
}