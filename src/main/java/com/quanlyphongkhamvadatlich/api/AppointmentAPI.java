package com.quanlyphongkhamvadatlich.api;


import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/doctor/appointments")
public class AppointmentAPI {

    @Autowired
    private IAppointmentService appointmentService;

//    @GetMapping("")
//    public String getAppointments(Model model) {
//        model.addAttribute("appointments", appointmentService.fillAll());
//        return "dashboard/doctor/appointment_schedule";
//    }
@GetMapping("")
public String getAllPages(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
    if (keyword == null || keyword.isEmpty()) {
        // Nếu không có ngày được cung cấp, hiển thị trang đầu tiên
        return getOnePage(model, 1);
    } else {
        try {
            Page<Appointment> page = appointmentService.findPage(5);
            int totalPages = page.getTotalPages();
            long totalItems = page.getTotalElements();
            List<Appointment> appointments = page.getContent();

            model.addAttribute("currentPage", 5);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("appointments", appointments);


            // Chuyển đổi chuỗi thành đối tượng Date với định dạng "yyyy-MM-dd"
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputDateFormat.parse(keyword.trim());

            // Tìm danh sách cuộc hẹn theo ngày
         //   List<Appointment> appointments = appointmentService.findByAppointmentDate(date);

            model.addAttribute("appointments", appointmentService.findByAppointmentDate(date));
            return "dashboard/doctor/appointment_schedule";
        } catch (ParseException e) {
            // Xử lý nếu chuỗi không thể được phân tích thành ngày
            e.printStackTrace();
            model.addAttribute("error", "Ngày không hợp lệ. Vui lòng chọn ngày khác.");
            return "dashboard/doctor/appointment_schedule"; // Trả về trang với thông báo lỗi
        }
    }
}

    @GetMapping("/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Appointment> page = appointmentService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Appointment> appointments = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("appointments", appointments);

        return "dashboard/doctor/appointment_schedule";
    }
    @RequestMapping("/{id}")
    @ResponseBody
    public Optional<Appointment> getAppointmentById(@PathVariable Long id){

        return appointmentService.findById(id);
    }



    @PostMapping
    public String createAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.createAppointment(appointment);
        return "redirect:/doctor/appointments"; // Redirect to appointment list page
    }

    @PutMapping("/{id}")
    public String updateAppointment(@PathVariable Long id, @ModelAttribute Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/doctor/appointments"; // Redirect to appointment list page
    }

    @GetMapping  ("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/doctor/appointments"; // Redirect to appointment list page
    }
}
