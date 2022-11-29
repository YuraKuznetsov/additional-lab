package com.labs.additional.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface EquationStorage {
    void saveEquation(String equation, String surfaceType) throws IOException;
    List<EquationInfo> getAllEquationsInfo() throws IOException;
}
