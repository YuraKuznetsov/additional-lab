package com.labs.additional.model;

import com.labs.additional.service.surface.calculation.SurfaceCalculator;
import com.labs.additional.service.surface.type.SurfaceType;
import com.labs.additional.service.surface.type.WideSurfaceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceTest {
    Surface surface;

    @BeforeEach
    void testSurface() {
        CoefficientsA coefficientsA =  new CoefficientsA(
                1, 0, -1, 0, 4,
                6.5, 2.5, -7, 0, 2
        );
        SurfaceValues surfaceValues = new SurfaceCalculator().calculateSurfaceValues(coefficientsA);
        WideSurfaceType wideSurfaceType = WideSurfaceType.FULL_SQUARE;
        SurfaceType surfaceType = SurfaceType.TWO_LEAF_HYPERBOLOID;
        surface = new Surface(coefficientsA, surfaceValues, wideSurfaceType, surfaceType);
    }

    @Test
    void getCoefficients() {
        CoefficientsA coefficientsA =  new CoefficientsA(
                1, 0, -1, 0, 4,
                6.5, 2.5, -7, 0, 2
        );
        assertEquals(coefficientsA, surface.getCoefficientsA());
    }

    @Test
    void getWideType() {
        assertEquals(WideSurfaceType.FULL_SQUARE, surface.getWideType());
    }

    @Test
    void getType() {
        assertEquals(SurfaceType.TWO_LEAF_HYPERBOLOID, surface.getType());
    }
}