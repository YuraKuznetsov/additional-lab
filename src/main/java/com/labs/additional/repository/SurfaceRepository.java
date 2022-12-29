package com.labs.additional.repository;

import com.labs.additional.model.Surface;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Component
public class SurfaceRepository {
    private final static String filePath = "src/main/resources/storage/surfaces_info.txt";

    public void saveSurface(Surface surface) throws IOException {
        String surfaceInformationRow = generateSurfaceInformationRow(surface);
        appendRow(surfaceInformationRow);
    }

    private String generateSurfaceInformationRow(Surface surface) {
        String userEquation = surface.getUserEquation();
        String canonicalEquation = surface.getCanonicalEquation();
        String surfaceType = surface.getSurfaceType();
        String imgSrc = surface.getImgSrc();

        return String.format("%s,%s,%s,%s\n", userEquation, canonicalEquation, surfaceType, imgSrc);
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
            Surface surface = getSurface(scanner.nextLine());
            surfaces.add(surface);
        }

        equationsFile.close();
        scanner.close();

        return surfaces;
    }

    private Surface getSurface(String line) {
        String[] splitLine = line.split(",");
        return new Surface(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
    }
}
