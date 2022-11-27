package com.labs.additional.surface;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SurfaceValidator {
    private final List<String> importantCoefficientKeys = List.of("a11", "a12", "a13", "a22", "a23", "a33");
    private final Set<String> allCoefficientKeys = Set.of("a11", "a12", "a13", "a22", "a23", "a33",
                                                       "a14", "a24", "a34", "a44");
    private final Map<String, String> request;

    public SurfaceValidator(Map<String, String> request) {
        this.request = request;
    }

    public boolean isCorrectRequest() {
        return hasAllCoefficients() && oneImportantCoefficientNotEmpty();
    }

    public boolean oneImportantCoefficientNotEmpty() {
        for (String coefficientKey : importantCoefficientKeys) {
            String requestCoefficient = request.get(coefficientKey);

            if (requestCoefficient == null) continue;
            if (!requestCoefficient.isEmpty()) return true;
        }

        return false;
    }

    public boolean hasAllCoefficients() {
        return allCoefficientKeys.equals(request.keySet());
    }
}
