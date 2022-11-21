package com.labs.additional;

import com.labs.additional.calculations.TypeFounder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/result")
    public String result(@RequestParam Map<String, String> request, Model model) {
        TypeFounder typeFounder = new TypeFounder(request);
        typeFounder.makeCalculations();
        model.addAttribute("founder", typeFounder);
        return "result";
    }
}
