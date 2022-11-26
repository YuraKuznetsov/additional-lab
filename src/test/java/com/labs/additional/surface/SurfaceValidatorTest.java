package com.labs.additional.surface;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceValidatorTest {

    @Test
    void false_whenRequestIsEmpty() {
        Map<String, String> request = Map.of();
        SurfaceValidator validator = new SurfaceValidator(request);
        assertFalse(validator.isCorrectRequest());
    }

    @Test
    void true_whenAtLeastOneNotEmptyCoefficient() {
        Map<String, String> request = Map.of("a11", "1");
        SurfaceValidator validator = new SurfaceValidator(request);
        assertTrue(validator.oneImportantCoefficientNotEmpty());
    }

    @Test
    void hasAllCoefficients() {
        Map<String, String> request = Map.of("a11", "2", "a22", "4", "a33", "1",
                "a44" ,"3", "a13","2", "a24", "4", "a12", "1",
                "a23", "0.5", "a34", "-5", "a14","10.6");
        SurfaceValidator validator = new SurfaceValidator(request);
        assertTrue(validator.hasAllCoefficients());
    }
}