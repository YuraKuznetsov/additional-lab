package com.labs.additional.dto;

import com.labs.additional.model.CoefficientsA;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceDTOTest {
    @Test
    void testSurfaceDTO() {
        CoefficientsA coefficientsA = new CoefficientsA(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<String, Double> values = Map.of(
                "I1", 1d,
                "I2", 2d,
                "I3", 3d,
                "I4", 4d,
                "K2", 20d,
                "K3", 30d,
                "lambda1", 1d,
                "lambda2", 2d,
                "lambda3", 3d
        );
        SurfaceDTO surfaceDTO = new SurfaceDTO(coefficientsA, values, "FULL_SQUARE", "Гіперболоїд дволистовий");

        assertEquals(coefficientsA, surfaceDTO.getCoefficientsA());
        assertEquals(values, surfaceDTO.getValues());
        assertEquals("FULL_SQUARE", surfaceDTO.getWideType());
        assertEquals("Гіперболоїд дволистовий", surfaceDTO.getType());
    }
}