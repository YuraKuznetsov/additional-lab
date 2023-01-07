package com.labs.additional.controller;

import com.labs.additional.dto.SurfaceDTO;
import com.labs.additional.dto.mapper.SurfaceMapper;
import com.labs.additional.model.Surface;
import com.labs.additional.service.surface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/surface")
public class SurfaceController {
    SurfaceService surfaceService;

    @Autowired
    public SurfaceController(SurfaceService surfaceService) {
        this.surfaceService = surfaceService;
    }

    @GetMapping("/")
    public String index() {
        return "surface/index";
    }

    @GetMapping("/results")
    public String results(Model model) {
        SurfaceMapper surfaceMapper = new SurfaceMapper();

        List<Surface> surfaceList = surfaceService.getAllSurfaces();
        List<SurfaceDTO> surfaceDTOList = surfaceList.stream()
                .map(surfaceMapper::getSurfaceDTO)
                .collect(Collectors.toList());

        model.addAttribute("surfaceList", surfaceDTOList);
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
    public String result(@RequestParam Map<String, String> equationCoefficients, Model model) {
        Surface surface = surfaceService.defineSurface(equationCoefficients);
        surfaceService.saveSurface(surface);

        SurfaceMapper surfaceMapper = new SurfaceMapper();
        model.addAttribute("surface", surfaceMapper.getSurfaceDTO(surface));

        return "surface/result";
    }
}
