package com.quanlyphongkhamvadatlich.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @GetMapping("/doctor/appointment_schedule")
    public ModelAndView appointmentSchedule() {
        return new ModelAndView("dashboard/doctor/appointment_schedule");
    }


    @GetMapping("/doctor/visits_statistics") //  thống kê sô lượt khám cua bác sĩ
    public ModelAndView VisitsStatistics() {
        return new ModelAndView("dashboard/doctor/visits_statistics");
    }

    @GetMapping("/doctor/history_exam")
    public ModelAndView historyExam() {
        return new ModelAndView("dashboard/doctor/history_exam");
    }


}
