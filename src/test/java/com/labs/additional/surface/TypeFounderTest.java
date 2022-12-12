package com.labs.additional.surface;

import com.labs.additional.service.surface.TypeFounder;
import com.labs.additional.service.surface.type.SurfaceType;
import com.labs.additional.service.surface.type.WideSurfaceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TypeFounderTest {
    TypeFounder typeFounder = new TypeFounder();

    @BeforeEach
    void setUp() {
        Map<String, Double> values = Map.of(
                "I1", -13.0, "I2", 28.5, "I3", 93.5, "I4", 396.56,
                "K2", -22.25, "K3", 251.75,
                "lambda1", -6.87, "lambda2", -7.86, "lambda3", 1.73);
        typeFounder.setValues(values);
    }

    @Test
    void testGetWideType() {
        assertEquals(WideSurfaceType.FULL_SQUARE, typeFounder.getWideType());
    }

    @Test
    void testGetType() {
        assertEquals(SurfaceType.ONE_LEAF_HYPERBOLOID, typeFounder.getType());
    }
}