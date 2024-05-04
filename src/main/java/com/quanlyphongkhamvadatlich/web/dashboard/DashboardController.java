package com.quanlyphongkhamvadatlich.web.dashboard;

import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("dashboard")
public class DashboardController {
    @GetMapping("/home")
    public ModelAndView toHomePage() {
        return new ModelAndView("dashboard/admin/index");
    }

    @GetMapping("/login")
    public String toLoginDashboard(@AuthenticationPrincipal UserPrincipal principal) {
        return principal != null ? "redirect:/dashboard/home" : "dashboard/login";
    }
}