package com.labs.additional.dto.mapper;

import com.labs.additional.dto.SurfaceDTO;
import com.labs.additional.model.Surface;
import com.labs.additional.service.surface.CoefficientsA;

import java.util.Map;

public class SurfaceMapper {
    public SurfaceDTO getSurfaceDTO(Surface surface) {
        CoefficientsA coefficientsA = surface.getCoefficientsA();
        Map<String, Double> values = Map.of(
                "I1", surface.getValues().getI1(),
                "I2", surface.getValues().getI2(),
                "I3", surface.getValues().getI3(),
                "I4", surface.getValues().getI4(),
                "K2", surface.getValues().getK2(),
                "K3", surface.getValues().getK3(),
                "lambda1", surface.getValues().getCubicRoots().getRoot1().orElse(null),
                "lambda2", surface.getValues().getCubicRoots().getRoot2().orElse(null),
                "lambda3", surface.getValues().getCubicRoots().getRoot3().orElse(null)
        );
        String wideType = surface.getWideType().toString();
        String type = surface.getType().getName();
        return new SurfaceDTO(coefficientsA, values, wideType, type);
    }
}
