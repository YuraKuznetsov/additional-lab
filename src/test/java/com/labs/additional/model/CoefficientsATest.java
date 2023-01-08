package com.labs.additional.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoefficientsATest {
    CoefficientsA coefficientsA;

    @Test
    void getCoefficients() {
        double a11 = 1;
        double a12 = 2;
        double a13 = 3;
        double a14 = 4;
        double a22 = 5;
        double a23 = 6;
        double a24 = 7;
        double a33 = 8;
        double a34 = 9;
        double a44 = 10;
        coefficientsA = new CoefficientsA(a11, a12, a13, a14, a22, a23, a24, a33, a34, a44);

        assertEquals(1, coefficientsA.getA11());
        assertEquals(2, coefficientsA.getA12());
        assertEquals(3, coefficientsA.getA13());
        assertEquals(4, coefficientsA.getA14());
        assertEquals(5, coefficientsA.getA22());
        assertEquals(6, coefficientsA.getA23());
        assertEquals(7, coefficientsA.getA24());
        assertEquals(8, coefficientsA.getA33());
        assertEquals(9, coefficientsA.getA34());
        assertEquals(10, coefficientsA.getA44());
    }
}