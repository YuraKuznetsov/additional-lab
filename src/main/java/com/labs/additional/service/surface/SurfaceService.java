package com.labs.additional.service.surface;

import com.labs.additional.model.Surface;
import com.labs.additional.repository.SurfaceRepository;
import com.labs.additional.service.surface.calculations.Calculator;
import com.labs.additional.service.surface.type.WideSurfaceType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SurfaceService {
    private final SurfaceRepository repository = new SurfaceRepository();
    private final Calculator calculator = new Calculator();
    private final TypeFounder typeFounder = new TypeFounder();

    public String getUserEquation(Map<String, String> request) {
        return EquationGenerator.generateUserEquation(request);
    }

    public Map<String, Double> getImportantValues(Map<String, String> request) {
        Map<String, Double> coefficients = CoefficientParser.getCoefficients(request);
        calculator.setCoefficients(coefficients);
        return calculator.calcValues();
    }

    public String getCubicEquation(Map<String, String> request) {
        Map<String, Double> values = getImportantValues(request);
        return EquationGenerator.getCubicEquation(values);
    }

    public String getCanonicalEquation(Map<String, String> request) {
        Map<String, Double> values = getImportantValues(request);
        typeFounder.setValues(values);
        WideSurfaceType wideSurfaceType = typeFounder.getWideType();
        return EquationGenerator.getCanonical(values, wideSurfaceType);
    }

    public String getType(Map<String, String> request) {
        Map<String, Double> values = getImportantValues(request);
        typeFounder.setValues(values);
        return typeFounder.getType().getName();
    }

    public List<Surface> getAllSurfaces() {
        try {
            return repository.getAllSurfaces();
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
}
