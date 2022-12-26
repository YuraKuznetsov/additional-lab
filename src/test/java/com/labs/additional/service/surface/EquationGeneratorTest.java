package com.labs.additional.service.surface;

import com.labs.additional.service.surface.type.WideSurfaceType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EquationGeneratorTest {

    @Test
    void emptyUserEquation_whenEmptyRequest() {
        Map<String, String> request = Map.of();
        assertEquals("", EquationGenerator.generateUserEquation(request));
    }

    @Test
    void testUserEquation() {
        Map<String, String> request = new LinkedHashMap<>();
        request.put("a11", "3");
        request.put("a33", "-8");
        request.put("a23", "3");
        request.put("a14", "-2");
        request.put("a44", "4");
        assertEquals("3x² - 8z² + 3yz - 2x + 4 = 0", EquationGenerator.generateUserEquation(request));
    }

    @Test
    void ignoreZeroAndEmptyCoefficientsInUserEquation() {
        Map<String, String> request = new LinkedHashMap<>();
        request.put("a11", "0");
        request.put("a22", "");
        request.put("a23", "2");
        request.put("a44", "4");

        assertEquals("2yz + 4 = 0", EquationGenerator.generateUserEquation(request));
    }

    @Test
    void testCubicEquation() {
        Map<String, Double> values = Map.of("I1", 4d, "I2", -3.4, "I3", -10.2);
        assertEquals("λ³ + -4.0λ² + -3.4λ + 10.2 = 0", EquationGenerator.getCubicEquation(values));
    }

    @Test
    void testSimpleCanonicalEquation() {
        Map<String, Double> values = Map.of("I1", 8d, "I2", -80d, "I3", -24d, "I4", -97d,
                "K2", 17.75, "K3", -324.5,
                "lambda1", .29, "lambda2", -6d, "lambda3", 13.71);
        String expected = "0.29x² + -6.0y² + 13.71z² + 4.04 = 0";
        String actual = EquationGenerator.getSimpleCanonical(values, WideSurfaceType.FULL_SQUARE);
        assertEquals(expected, actual);
    }

    @Test
    void canonicalEquation_whenTypeIsPlane() {
        Map<String, Double> values = Map.of("I1", 4d, "I2", 0d, "I3", 0d, "I4", 0d,
                                            "K2", 0d, "K3", 0d,
                                            "lambda1", 0d, "lambda2", 0d, "lambda3", 0d);
        String expected = "Не підтримується";
        String actual = EquationGenerator.getCanonical(values, WideSurfaceType.PLANE);
        assertEquals(expected, actual);
    }
}