package com.quanlyphongkhamvadatlich.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("admin")
public class AdminController {
    @GetMapping("/doctor")
    public ModelAndView toDoctor() {
        return new ModelAndView("dashboard/admin/doctor");
    }
    @GetMapping("/patient")
    public ModelAndView toPatient() {
        return new ModelAndView("dashboard/admin/patient");
    }
    @GetMapping("/service")
    public ModelAndView toPatientEdit() {
        return new ModelAndView("dashboard/admin/service");
    }
}
