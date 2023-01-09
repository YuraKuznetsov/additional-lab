package com.labs.additional.dto.mapper;

import com.labs.additional.dto.SurfaceDTO;
import com.labs.additional.model.CoefficientsA;
import com.labs.additional.model.Surface;
import com.labs.additional.model.SurfaceValues;
import com.labs.additional.service.surface.calculation.equation.cubic.CubicRoots;
import com.labs.additional.service.surface.type.SurfaceType;
import com.labs.additional.service.surface.type.WideSurfaceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceMapperTest {
    SurfaceMapper mapper = new SurfaceMapper();

    @Test
    void testSurfaceToDto() {
        CoefficientsA coefficientsA = new CoefficientsA(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        CubicRoots cubicRoots = new CubicRoots(1.0, 2.0, 3.0);
        SurfaceValues values = new SurfaceValues(1, 2, 3, 4, 20, 30, cubicRoots);
        WideSurfaceType wideType = WideSurfaceType.FULL_SQUARE;
        SurfaceType type = SurfaceType.TWO_LEAF_HYPERBOLOID;
        Surface surface = new Surface(coefficientsA, values, wideType, type);
        SurfaceDTO surfaceDTO = mapper.getSurfaceDTO(surface);

        assertEquals(coefficientsA, surfaceDTO.getCoefficientsA());

        assertEquals(1, surfaceDTO.getValues().get("I1"));
        assertEquals(2, surfaceDTO.getValues().get("I2"));
        assertEquals(3, surfaceDTO.getValues().get("I3"));
        assertEquals(4, surfaceDTO.getValues().get("I4"));

        assertEquals(20, surfaceDTO.getValues().get("K2"));
        assertEquals(30, surfaceDTO.getValues().get("K3"));

        assertEquals(1.0, surfaceDTO.getValues().get("lambda1"));
        assertEquals(2.0, surfaceDTO.getValues().get("lambda2"));
        assertEquals(3.0, surfaceDTO.getValues().get("lambda3"));

        assertEquals("FULL_SQUARE", surfaceDTO.getWideType());
        assertEquals("Гіперболоїд дволистовий", surfaceDTO.getType());
    }
}