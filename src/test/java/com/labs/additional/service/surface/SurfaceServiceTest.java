package com.labs.additional.service.surface;

import com.labs.additional.model.CoefficientsA;
import com.labs.additional.model.Surface;
import com.labs.additional.model.SurfaceValues;
import com.labs.additional.repository.SurfaceRepository;
import com.labs.additional.service.surface.calculation.SurfaceCalculator;
import com.labs.additional.service.surface.type.SurfaceType;
import com.labs.additional.service.surface.type.SurfaceTypeFounder;
import com.labs.additional.service.surface.type.WideSurfaceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SurfaceServiceTest {
    @Autowired
    SurfaceService surfaceService;
    @Autowired
    SurfaceRepository surfaceRepository;

    @Test
    void defineSurface() {
        Map<String, String> equationCoefficients = Map.of(
                "x²", "1",
                "y²", "4",
                "z²", "-7",
                "xy", "",
                "xz", "-2",
                "yz", "13",
                "x", "",
                "y", "5",
                "z", "",
                "constant", "2"
        );
        CoefficientsA coefficientsA =  new CoefficientsA(
                1, 0, -1, 0, 4,
                6.5, 2.5, -7, 0, 2
        );
        SurfaceValues surfaceValues = new SurfaceCalculator().calculateSurfaceValues(coefficientsA);

        SurfaceTypeFounder typeFounder = new SurfaceTypeFounder(surfaceValues);
        WideSurfaceType wideSurfaceType = typeFounder.getWideType();
        SurfaceType surfaceType = typeFounder.getType();

        Surface expected = new Surface(coefficientsA, surfaceValues, wideSurfaceType, surfaceType);
        Surface actual = surfaceService.defineSurface(equationCoefficients);

        assertEquals(expected, actual);
    }
}