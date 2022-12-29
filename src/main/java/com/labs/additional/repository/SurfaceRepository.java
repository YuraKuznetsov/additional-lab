package com.labs.additional.repository;

import com.labs.additional.model.Surface;
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
        String surfaceInformationRow = generateSurfaceInformationRow(surface);
        appendRow(surfaceInformationRow);
    }

    private String generateSurfaceInformationRow(Surface surface) {
        String userEquation = surface.getUserEquation();
        String valuesAsString = mapToString(surface.getValues());
        String cubicEquation = surface.getCubicEquation();
        String canonicalEquationExplain = surface.getCanonicalEquationExplain();
        String canonicalEquationFormula = surface.getCanonicalEquationFormula();
        String simpleCanonicalEquation = surface.getSimpleCanonicalEquation();
        String canonicalEquation = surface.getCanonicalEquation();
        String surfaceType = surface.getSurfaceType();
        String imgSrc = surface.getImgSrc();

        return String.format(
                "%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                userEquation,
                valuesAsString,
                cubicEquation,
                canonicalEquationExplain,
                canonicalEquationFormula,
                simpleCanonicalEquation,
                canonicalEquation,
                surfaceType,
                imgSrc);
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

        String userEquation = surfaceInfo[0];
        Map<String, Double> values = stringToMap(surfaceInfo[1]);
        String cubicEquation = surfaceInfo[2];
        String canonicalEquationExplain = surfaceInfo[3];
        String canonicalEquationFormula = surfaceInfo[4];
        String simpleCanonicalEquation = surfaceInfo[5];
        String canonicalEquation = surfaceInfo[6];
        String surfaceType = surfaceInfo[7];
        String imgSrc = surfaceInfo[8];

        return new Surface(userEquation, values, cubicEquation, canonicalEquationExplain,
                           canonicalEquationFormula, simpleCanonicalEquation,
                           canonicalEquation, surfaceType, imgSrc);
    }

    private String mapToString(Map<String, Double> map) {
        return map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(","));
    }

    private Map<String, Double> stringToMap(String string) {
        return Arrays.stream(string.split(","))
                .map(entry -> entry.split("="))
                .collect(Collectors.toMap(
                        entry -> entry[0],
                        entry -> Double.parseDouble(entry[1])
                ));
    }
}
