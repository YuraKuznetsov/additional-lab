package com.labs.additional.storage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileManager implements EquationStorage {
    private final String filePath = "src/main/java/com/labs/additional/storage/equations.txt";

    @Override
    public void saveEquation(String equation, String surfaceType) throws IOException {

        FileWriter equationsFile = new FileWriter(filePath, true);
        equationsFile.write(equation + "; " + surfaceType + "\n");
        equationsFile.close();
    }

    @Override
    public List<EquationInfo> getAllEquationsInfo() throws IOException {
        List<EquationInfo> equationsInfo = new LinkedList<>();
        FileReader equationsFile = new FileReader(filePath);
        Scanner scanner = new Scanner(equationsFile);

        while (scanner.hasNext()) {
            EquationInfo equationInfo = getEquationInfo(scanner.nextLine());
            equationsInfo.add(equationInfo);
        }

        equationsFile.close();
        scanner.close();

        return equationsInfo;
    }

    private EquationInfo getEquationInfo(String line) {
        String[] splitLine = line.split("; ");
        return new EquationInfo(splitLine[0], splitLine[1]);
    }
}
