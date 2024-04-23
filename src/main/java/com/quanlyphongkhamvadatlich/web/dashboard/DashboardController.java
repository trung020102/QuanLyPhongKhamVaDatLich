package com.quanlyphongkhamvadatlich.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    @GetMapping("/")
    public ModelAndView toHomePage() {
        return new ModelAndView("dashboard/admin/index");
    }
    @GetMapping("/doctor/physical_exam")
    public ModelAndView toPhysicalExam(){

        return new ModelAndView("dashboard/doctor/physical_exam");
    }
}
