package com.labs.additional.dto;

import com.labs.additional.service.surface.CoefficientsA;

import java.util.Map;

public class SurfaceDTO {
    private final CoefficientsA coefficientsA;
    private final Map<String, Double> values;
    private final String wideType;
    private final String type;

    public SurfaceDTO(CoefficientsA coefficientsA, Map<String, Double> values, String wideType, String type) {
        this.coefficientsA = coefficientsA;
        this.values = values;
        this.wideType = wideType;
        this.type = type;
    }

    public CoefficientsA getCoefficientsA() {
        return coefficientsA;
    }

    public Map<String, Double> getValues() {
        return values;
    }

    public String getWideType() {
        return wideType;
    }

    public String getType() {
        return type;
    }
}
