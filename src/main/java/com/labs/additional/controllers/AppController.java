package com.labs.additional.controllers;

import com.labs.additional.surface.CoefficientParser;
import com.labs.additional.surface.EquationGenerator;
import com.labs.additional.surface.TypeFounder;
import com.labs.additional.surface.calculations.Calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/result")
    public String result(@RequestParam Map<String, String> request, Model model) {
        Map<String, Double> coefficients = CoefficientParser.getCoefficients(request);

        Calculator calculator = new Calculator(coefficients);
        Map<String, Double> values = calculator.calcValues();

        TypeFounder typeFounder = new TypeFounder(values);
        String canonical = EquationGenerator.getCanonical(values, typeFounder.getWideType());
        String type = typeFounder.getType().getName();

        model.addAttribute("values", values);
        model.addAttribute("canonical", canonical);
        model.addAttribute("type", type);
        return "result";
    }
}
