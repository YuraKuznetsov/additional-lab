package com.labs.additional.surface;

import com.labs.additional.service.surface.EquationGenerator;
import com.labs.additional.service.surface.type.WideSurfaceType;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class EquationGeneratorTest {

    @Test
    void emptyUserEquation_whenEmptyRequest() {
        Map<String, String> request = Map.of();
        assertEquals("", EquationGenerator.getUserEquation(request));
    }

    @Test
    void ignoreZeroAndEmptyCoefficients() {
        TreeMap<String, String> request = new TreeMap<>();
        request.put("a11", "0");
        request.put("a22", "");
        request.put("a23", "2");
        request.put("a44", "4");

        assertEquals("2yz + 4 = 0", EquationGenerator.getUserEquation(request));
    }

    @Test
    void testGetCubicEquation() {
        Map<String, Double> values = Map.of("I1", 4d, "I2", -3.4, "I3", -10.2);
        assertEquals("λ³ + -4.0λ² + -3.4λ + 10.2 = 0", EquationGenerator.getCubicEquation(values));
    }

    @Test
    void testCanonical_whenTypeIsPlane() {
        Map<String, Double> values = Map.of("I1", 4d, "I2", 0d, "I3", 0d, "I4", 0d,
                                            "K2", 0d, "K3", 0d,
                                            "lambda1", 0d, "lambda2", 0d, "lambda3", 0d);

        assertEquals("Не підтримується", EquationGenerator.getCanonical(values, WideSurfaceType.PLANE));
    }
}