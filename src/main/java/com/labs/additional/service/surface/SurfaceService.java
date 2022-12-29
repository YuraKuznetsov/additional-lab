package com.labs.additional.service.surface;

import com.labs.additional.model.Surface;
import com.labs.additional.repository.SurfaceRepository;
import com.labs.additional.service.surface.calculations.Calculator;
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
    private final Calculator calculator;
    private final TypeFounder typeFounder;

    @Autowired
    public SurfaceService(SurfaceRepository repository) {
        this.repository = repository;
        this.calculator = new Calculator();
        this.typeFounder = new TypeFounder();
    }

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

    public String getCanonicalExplain(Map<String, String> request) {
        Map<String, Double> values = getImportantValues(request);
        typeFounder.setValues(values);

        switch (typeFounder.getWideType()) {
            case FULL_SQUARE -> {
                return "Оскільки I₃ не дорівнює нулеві, канонічне рівняння має вигляд:";
            }
            case PARABOLOID -> {
                return "Оскільки I₃ == 0, I₂ != 0, I₄ != 0, канонічне рівняння має вигляд:";
            }
            case CYLINDER -> {
                return "Оскільки I₃ == 0 && I₂ != 0 && I₄ == 0, канонічне рівняння має вигляд:";
            }
            case PARABOLIC_CYLINDER -> {
                return "Оскільки I₃ == 0 && I₂ == 0 && I₄ == 0 && I₁ != 0 && K₃ != 0, канонічне рівняння має вигляд:";
            }
            default -> {
                return "Жодна з умов не підтримується. Канонічного рівняння не існує";
            }
        }
    }

    public String getCanonicalFormula(Map<String, String> request) {
        Map<String, Double> values = getImportantValues(request);
        typeFounder.setValues(values);

        switch (typeFounder.getWideType()) {
            case FULL_SQUARE -> {
                return "λ₁x² + λ₂y² + λ₃z² + I₄ / I₃ = 0";
            }
            case PARABOLOID -> {
                return "λ₁x² + λ₂y² + 2z * (-I₄ / I₂)^(1/2) = 0";
            }
            case CYLINDER -> {
                return "λ₁x² + λ₂y² + K₃ / I₂ = 0";
            }
            case PARABOLIC_CYLINDER -> {
                return "λ₁x² + 2y * (-K₃ / I₁)^(1/2) = 0";
            }
            default -> {
                return "...";
            }
        }
    }

    public String getSimpleCanonical(Map<String, String> request) {
        Map<String, Double> values = getImportantValues(request);
        typeFounder.setValues(values);

        return EquationGenerator.getSimpleCanonical(values, typeFounder.getWideType());
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

    public String getImgSrc(Map<String, String> request) {
        typeFounder.setValues(getImportantValues(request));
        final String pathToFolder = "/images/surfaces/";

        switch (typeFounder.getType()) {
            case ELLIPSOID, IMAGINARY_ELLIPSOID, SPHERE, SPHEROID -> {
                return pathToFolder + "Ellipsoid.png";
            }
            case ONE_LEAF_HYPERBOLOID -> {
                return pathToFolder + "Hyperboloid_1.png";
            }
            case TWO_LEAF_HYPERBOLOID -> {
                return pathToFolder + "Hyperboloid_2.png";
            }
            case CONE -> {
                return pathToFolder + "Cone.png";
            }
            case CIRCULAR_PARABOLOID, ELLIPTICAL_PARABOLOID -> {
                return pathToFolder + "Elliptic_Paraboloid.png";
            }
            case HYPERBOLIC_PARABOLOID -> {
                return pathToFolder + "Hyperbolic_Paraboloid.png";
            }
            case CIRCULAR_CYLINDER, ELLIPTICAL_CYLINDER -> {
                return pathToFolder + "Elliptic_Cylinder.png";
            }
            case HYPERBOLIC_CYLINDER -> {
                return pathToFolder + "Hyperbolic_Cylinder.png";
            }
            case PARABOLIC_CYLINDER -> {
                return pathToFolder + "Parabolic_Cylinder.png";
            }
            default -> {
                return " ";
            }
        }
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
}
