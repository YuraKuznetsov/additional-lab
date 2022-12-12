package com.labs.additional.surface;

import static org.junit.jupiter.api.Assertions.*;

import com.labs.additional.service.surface.CoefficientParser;
import org.junit.jupiter.api.Test;

import java.util.Map;


class CoefficientParserTest {

    @Test
    void isEmpty_whenRequestIsEmpty() {
        Map<String, String> request = Map.of();
        assertTrue(CoefficientParser.getCoefficients(request).isEmpty());
    }

    @Test
    void notDivideSpecialValues() {
        Map<String, String> request = Map.of(
                "a11", "2", "a22", "4",
                "a33", "1", "a44" ,"3");

        Map<String, Double> expected = Map.of(
                "a11", 2., "a22", 4.,
                "a33", 1., "a44" ,3.);

        assertEquals(expected, CoefficientParser.getCoefficients(request));
    }

    @Test
    void DivideNotSpecialValues() {
        Map<String, String> request = Map.of(
                "a13","2", "a24", "4", "a12", "1",
                "a23", "0.5", "a34", "-5", "a14","10.6");

        Map<String, Double> expected = Map.of(
                "a13",1., "a24", 2., "a12", .5,
                "a23", .25, "a34", -2.5, "a14",5.3);

        assertEquals(expected, CoefficientParser.getCoefficients(request));
    }
}