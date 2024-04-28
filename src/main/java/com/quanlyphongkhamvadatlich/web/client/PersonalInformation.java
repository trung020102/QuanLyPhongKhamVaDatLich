package com.quanlyphongkhamvadatlich.web.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class PersonalInformation {
    @GetMapping("/personalinfo")
    public String personalinfo(){
        return "client/pages/personalinfo";
    }
}
