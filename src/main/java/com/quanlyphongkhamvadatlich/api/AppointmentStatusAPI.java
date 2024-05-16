package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor/appointments")
public class AppointmentStatusAPI {
    @Autowired
    IAppointmentService appointmentService;

    @GetMapping("/{id}/status1")
    public ResponseEntity<String> updateAppointmentStatus(@PathVariable(value = "id") Long id, @RequestParam(value = "statusId") Long statusId) {
        appointmentService.updateAppointmentStatus(id, statusId);
        return ResponseEntity.ok("Appointment status updated successfully");
    }
}
