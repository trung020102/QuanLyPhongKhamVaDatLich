package com.quanlyphongkhamvadatlich.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/doctor")
public class DashboardController {
    @GetMapping("/")
    public ModelAndView toHomePage() {
        return new ModelAndView("admin/index");
    }
    @GetMapping("/physical_exam")
    public ModelAndView toPhysicalExam(){

        return new ModelAndView("doctor/physical_exam");
    }


    @GetMapping("/appointment_schedule")
    public ModelAndView appointmentSchedule() {
        return new ModelAndView("doctor/appointment_schedule");
    }


    @GetMapping("/visits_statistics") //  thống kê sô lượt khám cua bác sĩ
    public ModelAndView VisitsStatistics() {
        return new ModelAndView("doctor/visits_statistics");
    }

    @GetMapping("/history_exam")
    public ModelAndView historyExam() {
        return new ModelAndView("doctor/history_exam");
    }


}
