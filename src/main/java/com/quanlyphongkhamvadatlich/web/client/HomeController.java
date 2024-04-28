package com.quanlyphongkhamvadatlich.web.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/client/login")
    public String login(){
        return "client/pages/login";
    }
    @GetMapping("/client/register")
    public String register(){
        return "client/pages/register";
    }
    @GetMapping("/client/changepassword")
    public String changepassword(){
        return "client/pages/changepassword";
    }
    @GetMapping("/client/forgotpassword")
    public String forgotpassword(){
        return "client/pages/forgotpassword";
    }
    @GetMapping("/client/personalinfo")
    public String personalinfo(){
        return "client/pages/personalinfo";
    }
    @GetMapping("/client/personalinfo/edit")
    public String editpersonalinfo(){
        return "client/pages/editpersonalinfo";
    }

    @GetMapping("client/home")
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

    @GetMapping("client/booking")
    public String booking() {
        return "client/pages/booking";
    }

    @GetMapping("client/booking/appointment")
    public String appointment() {
        return "client/pages/appointment";
    }

    @GetMapping("client/booking/appointment/success")
    public String success() {
        return "client/pages/success-booking";
    }

    @GetMapping("/client/record")
    public String record() {return "client/pages/record";}
}
