package com.quanlyphongkhamvadatlich.web.dashboard;

import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (principal != null)
            return "redirect:/dashboard/home";

        return "dashboard/login";
    }

    @GetMapping("/logout")
    public String logout() {
        // Xóa bất kỳ phiên đăng nhập nào hiện đang tồn tại
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/dashboard/login"; // Điều hướng đến trang đăng nhập và thông báo đăng xuất thành công
    }
}