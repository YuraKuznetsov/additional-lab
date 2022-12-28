package com.labs.additional.controller;

import com.labs.additional.model.Surface;
import com.labs.additional.service.surface.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/surface")
public class SurfaceController {

    SurfaceService surfaceService = new SurfaceService();

    @GetMapping("/")
    public String index() {
        return "surface/index";
    }

    @GetMapping("/results")
    public String results(Model model) {
        List<Surface> surfaces = surfaceService.getAllSurfaces();
        Collections.reverse(surfaces);
        model.addAttribute("surfaces", surfaces);

        return "surface/results";
    }

    @GetMapping("/available")
    public String available() {
        return "surface/available";
    }

    @GetMapping("/algorithm")
    public String algorithm() {
        return "surface/algorithm";
    }

    @PostMapping("/result")
    public String result(@RequestParam Map<String, String> request, Model model) {
        SurfaceValidator validator = new SurfaceValidator(request);
        if (!validator.isCorrectRequest()) {
            return "surface/resultErrorPage";
        }

        String userEquation = surfaceService.getUserEquation(request);
        Map<String, Double> importantValues = surfaceService.getImportantValues(request);
        String cubicEquation = surfaceService.getCubicEquation(request);
        String canonicalExplain = surfaceService.getCanonicalExplain(request);
        String canonicalFormula = surfaceService.getCanonicalFormula(request);
        String simpleCanonicalEquation = surfaceService.getSimpleCanonical(request);
        String canonicalEquation = surfaceService.getCanonicalEquation(request);
        String surfaceType = surfaceService.getType(request);
        String imgSrc = surfaceService.getImgSrc(request);

        model.addAttribute("userEquation", userEquation);
        model.addAttribute("values", importantValues);
        model.addAttribute("cubicEquation", cubicEquation);
        model.addAttribute("canonicalExplain", canonicalExplain);
        model.addAttribute("canonicalFormula", canonicalFormula);
        model.addAttribute("simpleCanonicalEquation", simpleCanonicalEquation);
        model.addAttribute("canonicalEquation", canonicalEquation);
        model.addAttribute("surfaceType", surfaceType);
        model.addAttribute("imgSrc", imgSrc);

        surfaceService.saveSurface(new Surface(userEquation, canonicalEquation, surfaceType, imgSrc));

        return "surface/result";
    }
}
