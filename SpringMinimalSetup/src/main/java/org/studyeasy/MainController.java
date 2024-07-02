package org.studyeasy;

import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    public String home(){
        return "home";
    }
}
