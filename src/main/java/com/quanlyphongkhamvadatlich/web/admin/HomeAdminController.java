package com.quanlyphongkhamvadatlich.web.admin;

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
@RequestMapping("/admin")
public class HomeAdminController {
    @GetMapping("/login")
    public String toLoginAdmin(@AuthenticationPrincipal UserPrincipal principal) {
        if (principal != null)
            if(principal.isAdmin())
                return "redirect:/admin/patient";

        return "dashboard/admin/login";
    }
   /* @GetMapping("/login")
    public String getAdminLoginPage() {
        return "dashboard/admin/login"; // Trả về giao diện đăng nhập cho vai trò ADMIN
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
        return "redirect:/admin/login"; // Điều hướng đến trang đăng nhập và thông báo đăng xuất thành công
    }
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

    @GetMapping("/visits_statistics") //  thống kê sô lượt khám cua bác sĩ
    public ModelAndView VisitsStatistics() {
        return new ModelAndView("dashboard/admin/visits_statistics");
    }

}
