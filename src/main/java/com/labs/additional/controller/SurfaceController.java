package com.labs.additional.controller;

import com.labs.additional.model.Surface;
import com.labs.additional.service.surface.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class SurfaceController {

    @GetMapping("/surface")
    public String index(Model model) {
        SurfaceService surfaceService = new SurfaceService();
        List<Surface> surfaces = surfaceService.getAllSurfaces();
        model.addAttribute("surfaces", surfaces);
        return "index";
    }

    @GetMapping("/result")
    public String result(@RequestParam Map<String, String> request, Model model) {
        SurfaceValidator validator = new SurfaceValidator(request);
        if (!validator.isCorrectRequest()) {
            return "resultErrorPage";
        }

        SurfaceService surfaceService = new SurfaceService();

        String userEquation = surfaceService.getUserEquation(request);
        Map<String, Double> importantValues = surfaceService.getImportantValues(request);
        String cubicEquation = surfaceService.getCubicEquation(request);
        String canonicalEquation = surfaceService.getCanonicalEquation(request);
        String surfaceType = surfaceService.getType(request);

        Surface surface = new Surface(userEquation, surfaceType, canonicalEquation);
        surfaceService.saveSurface(surface);

        model.addAttribute("userEquation", userEquation);
        model.addAttribute("values", importantValues);
        model.addAttribute("cubicEquation", cubicEquation);
        model.addAttribute("canonical", canonicalEquation);
        model.addAttribute("type", surfaceType);

        return "result";
    }
}
