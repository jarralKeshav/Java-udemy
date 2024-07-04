package com.studyeasy.springstarter.Controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model) {
        return "home";

    }
}
