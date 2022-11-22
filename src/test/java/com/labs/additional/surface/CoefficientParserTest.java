package com.labs.additional.surface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;


class CoefficientParserTest {

    @Test
    void getCoefficients() {
        Map<String, String> request1 = Map.of(
                "a11", "", "a22", "", "a33", "", "a44" ,"", "a13","",
                "a24", "", "a12", "", "a23", "", "a34", "", "a14","");
        Map<String, Double> expect1 = Map.of(
                "a11", 0.0, "a22", 0.0, "a33", 0.0, "a44" ,0.0, "a13",0.0,
                "a24", 0.0, "a12", 0.0, "a23", 0.0, "a34", 0.0, "a14",0.0);
        Map<String, Double> result1 = CoefficientParser.getCoefficients(request1);
        Assertions.assertEquals(expect1, result1);

        Map<String, String> request2 = Map.of(
                "a11", "2", "a22", "2", "a33", "2", "a44" ,"2", "a13","2",
                "a24", "2", "a12", "2", "a23", "2", "a34", "2", "a14","2");
        Map<String, Double> expect2 = Map.of(
                "a11", 2.0, "a22", 2.0, "a33", 2.0, "a44" ,2.0, "a13",1.0,
                "a24", 1.0, "a12", 1.0, "a23", 1.0, "a34", 1.0, "a14",1.0);
        Map<String, Double> result2 = CoefficientParser.getCoefficients(request2);
        Assertions.assertEquals(expect2, result2);
    }
}