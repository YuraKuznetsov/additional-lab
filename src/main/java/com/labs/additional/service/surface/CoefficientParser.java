package com.labs.additional.service.surface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoefficientParser {
    private static final List<String> notDividedValues = List.of("a11", "a22", "a33", "a44");
    static public Map<String, Double> getCoefficients(Map<String, String> request) {
        Map<String, Double> coefficients = new HashMap<>();

        for (String key : request.keySet()) {
            if (notDividedValues.contains(key)) {
                coefficients.put(key, stringToDouble(request.get(key)));
            } else {
                coefficients.put(key, stringToDouble(request.get(key)) / 2);
            }
        }

        return coefficients;
    }

    static private double stringToDouble(String string) {
        if (string.isEmpty()) return 0.0;
        return Double.parseDouble(string);
    }
}
