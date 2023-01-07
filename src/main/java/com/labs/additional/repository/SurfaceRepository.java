package com.labs.additional.repository;

import com.labs.additional.model.Surface;
import com.labs.additional.model.CoefficientsA;
import com.labs.additional.model.SurfaceValues;
import com.labs.additional.service.surface.calculation.equation.cubic.CubicRoots;
import com.labs.additional.service.surface.type.SurfaceType;
import com.labs.additional.service.surface.type.WideSurfaceType;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SurfaceRepository {
    private final String filePath = "src/main/resources/storage/surfaces_info.txt";

    public void saveSurface(Surface surface) throws IOException {
        String surfaceInformationRow = surfaceToString(surface);
        appendRow(surfaceInformationRow);
    }

    private String surfaceToString(Surface surface) {
        String coefficientsAsString = coefficientsAToString(surface.getCoefficientsA());
        String valuesAsString = surfaceValuesToString(surface.getValues());

        return String.format(
                "%s;%s;%s;%s\n",
                coefficientsAsString,
                valuesAsString,
                surface.getWideType(),
                surface.getType());
    }

    private String coefficientsAToString(CoefficientsA coefficientsA) {
        return String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                coefficientsA.getA11(),
                coefficientsA.getA12(),
                coefficientsA.getA13(),
                coefficientsA.getA14(),
                coefficientsA.getA22(),
                coefficientsA.getA23(),
                coefficientsA.getA24(),
                coefficientsA.getA33(),
                coefficientsA.getA34(),
                coefficientsA.getA44()
        );
    }

    private String surfaceValuesToString(SurfaceValues values) {
        return String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s,%s",
                values.getI1(),
                values.getI2(),
                values.getI3(),
                values.getI4(),
                values.getK2(),
                values.getK3(),
                values.getCubicRoots().getRoot1(),
                values.getCubicRoots().getRoot2().orElse(0.0),
                values.getCubicRoots().getRoot3().orElse(0.0)
        );
    }

    private void appendRow(String row) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(row);
        writer.close();
    }

    public List<Surface> getAllSurfaces() throws IOException {
        List<Surface> surfaces = new LinkedList<>();
        FileReader equationsFile = new FileReader(filePath);
        Scanner scanner = new Scanner(equationsFile);

        while (scanner.hasNext()) {
            Surface surface = generateSurface(scanner.nextLine());
            surfaces.add(surface);
        }

        equationsFile.close();
        scanner.close();

        return surfaces;
    }

    private Surface generateSurface(String line) {
        String[] surfaceInfo = line.split(";");

        CoefficientsA coefficientsA = stringToCoefficientsA(surfaceInfo[0]);
        SurfaceValues values = getSurfaceValues(surfaceInfo[1]);
        WideSurfaceType wideType = WideSurfaceType.valueOf(surfaceInfo[2]);
        SurfaceType type = SurfaceType.valueOf(surfaceInfo[3]);

        return new Surface(coefficientsA, values, wideType, type);
    }

    private CoefficientsA stringToCoefficientsA(String string) {
        String[] coefficients = string.split(",");

        double a11 = Double.parseDouble(coefficients[0]);
        double a12 = Double.parseDouble(coefficients[1]);
        double a13 = Double.parseDouble(coefficients[2]);
        double a14 = Double.parseDouble(coefficients[3]);
        double a22 = Double.parseDouble(coefficients[4]);
        double a23 = Double.parseDouble(coefficients[5]);
        double a24 = Double.parseDouble(coefficients[6]);
        double a33 = Double.parseDouble(coefficients[7]);
        double a34 = Double.parseDouble(coefficients[8]);
        double a44 = Double.parseDouble(coefficients[9]);

        return new CoefficientsA(a11, a12, a13, a14, a22, a23, a24, a33, a34, a44);
    }

    private SurfaceValues getSurfaceValues(String string) {
        List<Double> values = Arrays.stream(string.split(","))
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        CubicRoots lambdas = new CubicRoots(values.get(6), values.get(7), values.get(8));

        return new SurfaceValues(
                values.get(0), values.get(1), values.get(2), values.get(3),
                values.get(4), values.get(5), lambdas);
    }
}
