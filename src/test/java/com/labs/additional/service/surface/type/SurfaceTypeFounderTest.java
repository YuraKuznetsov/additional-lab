package com.labs.additional.service.surface.type;

import com.labs.additional.model.SurfaceValues;
import com.labs.additional.service.surface.calculation.equation.cubic.CubicRoots;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceTypeFounderTest {
    SurfaceTypeFounder surfaceTypeFounder;

    @Test
    void testGetWideType() {
        CubicRoots cubicRoots = new CubicRoots(-6.87, -7.86, 1.73);
        SurfaceValues surfaceValues = new SurfaceValues(
                -13.0, 28.5, 93.5, 396.56,
                -22.25, 251.75, cubicRoots
        );
        surfaceTypeFounder = new SurfaceTypeFounder(surfaceValues);

        WideSurfaceType expectedType = WideSurfaceType.FULL_SQUARE;
        WideSurfaceType actualType = surfaceTypeFounder.getWideType();
        assertEquals(expectedType, actualType);
    }

    @Test
    void testGetType() {
        CubicRoots cubicRoots = new CubicRoots(-6.87, -7.86, 1.73);
        SurfaceValues surfaceValues = new SurfaceValues(
                -13.0, 28.5, 93.5, 396.56,
                -22.25, 251.75, cubicRoots
        );
        surfaceTypeFounder = new SurfaceTypeFounder(surfaceValues);

        SurfaceType expectedType = SurfaceType.ONE_LEAF_HYPERBOLOID;
        SurfaceType actualType = surfaceTypeFounder.getType();
        assertEquals(expectedType, actualType);
    }
}