package com.quanlyphongkhamvadatlich.web.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class AuthenticationController {
    
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
}
