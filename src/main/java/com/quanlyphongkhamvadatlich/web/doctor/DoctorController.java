package com.quanlyphongkhamvadatlich.web.doctor;

import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @GetMapping("/login")
    public String toLoginDoctor(@AuthenticationPrincipal UserPrincipal principal) {
        if (principal != null)
            if(principal.isDoctor())
                 return "redirect:/doctor/physical_exam";

        return "dashboard/doctor/login";
    }
   /*@GetMapping("/login")
   public String getDoctorLoginPage() {
       return "dashboard/doctor/login"; // Trả về giao diện đăng nhập cho vai trò ADMIN
   }*/

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Xóa phiên đăng nhập
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        // Xóa session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/doctor/login"; // Điều hướng đến trang đăng nhập và thông báo đăng xuất thành công
    }

    @GetMapping("/history_exam")
    public ModelAndView historyExam() {
        return new ModelAndView("dashboard/doctor/history_exam");
    }

    @GetMapping("/physical_exam")
    public ModelAndView toPhysicalExam(){
        return new ModelAndView("dashboard/doctor/physical_exam");
    }
    @GetMapping("/appointment_schedule")
    public ModelAndView appointmentSchedule() {
        return new ModelAndView("dashboard/doctor/appointment_schedule");
    }

}
