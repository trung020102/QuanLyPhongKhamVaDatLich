package com.quanlyphongkhamvadatlich.web.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class HomeController {
    @GetMapping("/login")
    public String login(){
        return "client/pages/login";
    }
    @GetMapping("/register")
    public String register(){
        return "client/pages/register";
    }
    @GetMapping("/changepassword")
    public String changepassword(){
        return "client/pages/changepassword";
    }
    @GetMapping("/forgotpassword")
    public String forgotpassword(){
        return "client/pages/forgotpassword";
    }
    @GetMapping("/personalinfo")
    public String personalinfo(){
        return "client/pages/personalinfo";
    }

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("activePage", "home");
        return "client/pages/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("activePage", "about");
        return "client/pages/about";
    }

    @GetMapping("/procedure")
    public String procedure(Model model) {
        model.addAttribute("activePage", "procedure");
        return "client/pages/procedure";
    }

    @GetMapping("/faqs")
    public String faqs(Model model) {
        model.addAttribute("activePage", "faqs");
        return "client/pages/faqs";
    }

    @GetMapping("/booking")
    public String booking() {
        return "client/pages/booking";
    }

    @GetMapping("/booking/appointment")
    public String appointment() {
        return "client/pages/appointment";
    }

    @GetMapping("/booking/appointment/success")
    public String success() {
        return "client/pages/success-booking";
    }
}
