package com.quanlyphongkhamvadatlich.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("activePage", "home");
        return "user/pages/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("activePage", "about");
        return "user/pages/about";
    }

    @GetMapping("/procedure")
    public String procedure(Model model) {
        model.addAttribute("activePage", "procedure");
        return "user/pages/procedure";
    }

    @GetMapping("/faqs")
    public String faqs(Model model) {
        model.addAttribute("activePage", "faqs");
        return "user/pages/faqs";
    }
}
