package com.quanlyphongkhamvadatlich.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String login(){
        return "user/pages/login";
    }
    @GetMapping("/register")
    public String register(){
        return "user/pages/register";
    }
    @GetMapping("/changepassword")
    public String changepassword(){
        return "user/pages/changepassword";
    }
    @GetMapping("/forgotpassword")
    public String forgotpassword(){
        return "user/pages/forgotpassword";
    }
    @GetMapping("/personalinfo")
    public String personalinfo(){
        return "user/pages/personalinfo";
    }

    @GetMapping("/home")
    public String index() {
        return "user/pages/index";
    }

    @GetMapping("/about")
    public String about() {
        return "user/pages/about";
    }

    @GetMapping("/procedure")
    public String procedure() {
        return "user/pages/procedure";
    }
}
