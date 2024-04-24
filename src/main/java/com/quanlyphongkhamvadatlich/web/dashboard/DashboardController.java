package com.quanlyphongkhamvadatlich.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    @GetMapping("/")
    public ModelAndView toHomePage() {
        return new ModelAndView("admin/index");
    }
    @GetMapping("/doctor/physical_exam")
    public ModelAndView toPhysicalExam(){

        return new ModelAndView("doctor/physical_exam");
    }
    @GetMapping("/admin/doctor")
    public ModelAndView toDoctor() {
        return new ModelAndView("admin/doctor");
    }
    @GetMapping("/admin/doctor_edit")
    public ModelAndView toDoctorEdit() {
        return new ModelAndView("admin/doctor_edit");
    }
    @GetMapping("/admin/patient")
    public ModelAndView toPatient() {
        return new ModelAndView("admin/patient");
    }
    @GetMapping("/admin/patient_edit")
    public ModelAndView toPatientEdit() {
        return new ModelAndView("admin/patient_edit");
    }
}
