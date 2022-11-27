package com.labs.additional.surface;

import com.labs.additional.surface.type.SurfaceType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TypeFounderTest {

    @Test
    void plane_whenAllValuesAreZero() {
        Map<String, Double> values = Map.of(
                "I1", 0d, "I2", 0d, "I3", 0d, "I4", 0d,
                "K2", 0d, "K3", 0d,
                "lambda1", 0d, "lambda2", 0d, "lambda3", 0d);
        assertEquals(SurfaceType.PLANE, new TypeFounder(values).getType());
    }
}