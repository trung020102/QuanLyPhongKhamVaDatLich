package com.quanlyphongkhamvadatlich.web.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class BookingController {
    
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