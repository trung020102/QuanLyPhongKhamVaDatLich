package com.quanlyphongkhamvadatlich.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String index() {
        return "user/pages/index";
    }

    @GetMapping("/about")
    public String about() {
        return "user/pages/about";
    }

    @GetMapping("/procedure")
    public String procedure() { return "user/pages/procedure";}

    @GetMapping("/booking")
    public String booking() { return "user/pages/booking";}

    @GetMapping("/booking/appointment")
    public String appointment() { return  "user/pages/appointment";}

    @GetMapping("/booking/appointment/success")
    public String success() { return  "user/pages/success-booking";}
}
