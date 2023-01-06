package com.labs.additional.service.surface.calculation;

import com.labs.additional.model.CoefficientsA;
import com.labs.additional.model.SurfaceValues;
import com.labs.additional.service.surface.calculation.equation.cubic.CubicRoots;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceCalculatorTest {
    SurfaceCalculator surfaceCalculator;

    @Test
    void zeroValues_whenAllCoefficientAreZero() {
        CoefficientsA zeroCoefficients = new CoefficientsA(
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0
        );

        surfaceCalculator = new SurfaceCalculator();
        SurfaceValues surfaceValues = surfaceCalculator.calculateSurfaceValues(zeroCoefficients);

        assertEquals(0, surfaceValues.getI1());
        assertEquals(0, surfaceValues.getI2());
        assertEquals(0, surfaceValues.getI3());
        assertEquals(0, surfaceValues.getI4());
        assertEquals(0, surfaceValues.getK2());
        assertEquals(0, surfaceValues.getK3());

        CubicRoots lambdas = surfaceValues.getCubicRoots();
        Optional<Double> lambda1 = lambdas.getRoot1();
        Optional<Double> lambda2 = lambdas.getRoot2();
        Optional<Double> lambda3 = lambdas.getRoot3();

        assertTrue(lambda1.isPresent());
        assertTrue(lambda2.isPresent());
        assertTrue(lambda3.isPresent());

        assertEquals(0, lambda1.get());
        assertEquals(0, lambda2.get());
        assertEquals(0, lambda3.get());
    }

    @Test
    void testCalcValues() {
        CoefficientsA coefficientsA = new CoefficientsA(
                3d, 0d, 0d, -1d, -2d,
                2.5, 0d, 0d, 0d, 0d
        );
        surfaceCalculator = new SurfaceCalculator();
        SurfaceValues surfaceValues = surfaceCalculator.calculateSurfaceValues(coefficientsA);

        assertEquals(1, surfaceValues.getI1());
        assertEquals(-12.25, surfaceValues.getI2());
        assertEquals(-18.75, surfaceValues.getI3());
        assertEquals(6.25, surfaceValues.getI4());
        assertEquals(-1, surfaceValues.getK2());
        assertEquals(2, surfaceValues.getK3());

        CubicRoots lambdas = surfaceValues.getCubicRoots();
        Optional<Double> lambda1 = lambdas.getRoot1();
        Optional<Double> lambda2 = lambdas.getRoot2();
        Optional<Double> lambda3 = lambdas.getRoot3();

        assertTrue(lambda1.isPresent());
        assertTrue(lambda2.isPresent());
        assertTrue(lambda3.isPresent());

        assertEquals(1.69, lambda1.get());
        assertEquals(-3.69, lambda2.get());
        assertEquals(3, lambda3.get());

    }
}