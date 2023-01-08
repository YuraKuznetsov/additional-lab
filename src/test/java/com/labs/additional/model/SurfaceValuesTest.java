package com.labs.additional.model;

import com.labs.additional.service.surface.calculation.equation.cubic.CubicRoots;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceValuesTest {

    @Test
    void testValues() {
        CubicRoots cubicRoots = new CubicRoots(1.0, 2.0, 3.0);
        SurfaceValues values = new SurfaceValues(1, 2, 3, 4, 20, 30, cubicRoots);

        assertEquals(1, values.getI1());
        assertEquals(2, values.getI2());
        assertEquals(3, values.getI3());
        assertEquals(4, values.getI4());
        assertEquals(20, values.getK2());
        assertEquals(30, values.getK3());
        assertEquals(1, values.getCubicRoots().getRoot1());
        assertEquals(2, values.getCubicRoots().getRoot2().get());
        assertEquals(3, values.getCubicRoots().getRoot3().get());
    }

}