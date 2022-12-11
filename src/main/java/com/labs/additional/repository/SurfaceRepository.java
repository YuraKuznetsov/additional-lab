package com.labs.additional.repository;

import com.labs.additional.model.Surface;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SurfaceRepository {
    private final static String filePath = "src/main/resources/storage/equations.txt";

    public void saveSurface(String equation, String surfaceType) throws IOException {
        FileWriter equationsFile = new FileWriter(filePath, true);
        equationsFile.write(equation + "; " + surfaceType + "\n");
        equationsFile.close();
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
        String[] splitLine = line.split("; ");
        return new Surface(splitLine[0], splitLine[1], "");
    }
}
