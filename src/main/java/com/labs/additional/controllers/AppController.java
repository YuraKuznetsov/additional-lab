package com.labs.additional.controllers;

import com.labs.additional.storage.EquationInfo;
import com.labs.additional.storage.EquationStorage;
import com.labs.additional.storage.FileManager;
import com.labs.additional.surface.CoefficientParser;
import com.labs.additional.surface.EquationGenerator;
import com.labs.additional.surface.SurfaceValidator;
import com.labs.additional.surface.TypeFounder;
import com.labs.additional.surface.calculations.Calculator;

import com.labs.additional.surface.type.SurfaceType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @GetMapping("/")
    public String index(Model model) {
        EquationStorage storage = new FileManager();
        List<EquationInfo> equations;

        try {
            equations = storage.getAllEquationsInfo();
        } catch (IOException e) {
            equations = List.of();
        }
        model.addAttribute("equations", equations);

        return "index";
    }

    @GetMapping("/result")
    public String result(@RequestParam Map<String, String> request, Model model) {
        SurfaceValidator validator = new SurfaceValidator(request);
        if (!validator.isCorrectRequest()) {
            return "resultErrorPage";
        }

        Map<String, Double> coefficients = CoefficientParser.getCoefficients(request);

        Calculator calculator = new Calculator(coefficients);
        Map<String, Double> values = calculator.calcValues();

        TypeFounder typeFounder = new TypeFounder(values);

        String userEquation = EquationGenerator.getUserEquation(request);
        SurfaceType surfaceType = typeFounder.getType();

        String canonical = surfaceType != SurfaceType.PLANE ?
                EquationGenerator.getCanonical(values, typeFounder.getWideType()) : "Не підтримується";

        EquationStorage storage = new FileManager();
        try {
            storage.saveEquation(userEquation, surfaceType.getName());
        } catch (IOException e) {
            return "resultErrorPage";
        }

        model.addAttribute("userEquation", userEquation);
        model.addAttribute("values", values);
        model.addAttribute("cubicEquation", EquationGenerator.getCubicEquation(values));
        model.addAttribute("canonical", canonical);
        model.addAttribute("type", surfaceType.getName());

        return "result";
    }
}
