package com.labs.additional.service.surface;

import com.labs.additional.model.CoefficientsA;
import com.labs.additional.model.Surface;
import com.labs.additional.model.SurfaceValues;
import com.labs.additional.repository.SurfaceRepository;
import com.labs.additional.service.surface.calculation.SurfaceCalculator;
import com.labs.additional.service.surface.type.SurfaceType;
import com.labs.additional.service.surface.type.SurfaceTypeFounder;
import com.labs.additional.service.surface.type.WideSurfaceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class SurfaceService {
    private final SurfaceRepository repository;

    @Autowired
    public SurfaceService(SurfaceRepository repository) {
        this.repository = repository;
    }

    public List<Surface> getAllSurfaces() {
        try {
            List<Surface> surfaces = repository.getAllSurfaces();
            Collections.reverse(surfaces);
            return surfaces;
        } catch (IOException e) {
            System.out.println("Can't get surfaces!");
            return List.of();
        }
    }

    public void saveSurface(Surface surface) {
        try {
            repository.saveSurface(surface);
        } catch (IOException e) {
            System.out.println("Can't save surface");
        }
    }

    public Surface defineSurface(Map<String, String> equationCoefficients) {
        CoefficientsA coefficientsA = getCoefficientsA(equationCoefficients);

        SurfaceCalculator surfaceCalculator = new SurfaceCalculator();
        SurfaceValues surfaceValues = surfaceCalculator.calculateSurfaceValues(coefficientsA);

        SurfaceTypeFounder typeFounder = new SurfaceTypeFounder(surfaceValues);
        WideSurfaceType wideSurfaceType = typeFounder.getWideType();
        SurfaceType surfaceType = typeFounder.getType();

        return new Surface(coefficientsA, surfaceValues, wideSurfaceType, surfaceType);
    }

    private CoefficientsA getCoefficientsA(Map<String, String> equationCoefficients) {
        double a11 = getCoefficientA(equationCoefficients, "x²");
        double a12 = getCoefficientA(equationCoefficients, "xy");
        double a13 = getCoefficientA(equationCoefficients, "xz");
        double a14 = getCoefficientA(equationCoefficients, "x");
        double a22 = getCoefficientA(equationCoefficients, "y²");
        double a23 = getCoefficientA(equationCoefficients, "yz");
        double a24 = getCoefficientA(equationCoefficients, "y");
        double a33 = getCoefficientA(equationCoefficients, "z²");
        double a34 = getCoefficientA(equationCoefficients, "z");
        double a44 = getCoefficientA(equationCoefficients, "constant");

        return new CoefficientsA(a11, a12, a13, a14, a22, a23, a24, a33, a34, a44);
    }

    private double getCoefficientA(Map<String, String> request, String EquationVariable) {
        String equationVariableCoefficient = request.get(EquationVariable);

        if (equationVariableCoefficient.isEmpty()) return 0.0;

        if (List.of("x²", "y²", "z²", "constant").contains(EquationVariable)) {
            return Double.parseDouble(equationVariableCoefficient);
        } else {
            return Double.parseDouble(equationVariableCoefficient) / 2;
        }
    }
}
