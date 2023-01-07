package com.labs.additional.service.surface.calculation.equation.cubic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubicRootsTest {
    CubicRoots cubicRoots;

    @Test
    void oneRoot() {
        cubicRoots = new CubicRoots(3.2);

        assertTrue(cubicRoots.getRoot2().isEmpty());
        assertTrue(cubicRoots.getRoot3().isEmpty());
        assertEquals(3.2, cubicRoots.getRoot1());
    }

    @Test
    void threeRoots() {
        cubicRoots = new CubicRoots(3.2, -2.3, 1.5);

        assertTrue(cubicRoots.getRoot2().isPresent());
        assertTrue(cubicRoots.getRoot3().isPresent());

        assertEquals(3.2, cubicRoots.getRoot1());
        assertEquals(-2.3, cubicRoots.getRoot2().get());
        assertEquals(1.5, cubicRoots.getRoot3().get());
    }
}