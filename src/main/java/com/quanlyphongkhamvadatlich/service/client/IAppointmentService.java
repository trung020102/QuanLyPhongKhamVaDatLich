package com.quanlyphongkhamvadatlich.service.client;

import java.util.Date;
import java.util.List;

import com.quanlyphongkhamvadatlich.dto.client.ApiResponse;
import com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;

import jakarta.mail.MessagingException;

public interface IAppointmentService {
    List<AutoSchedulerEmailNotifierDTO> findByAppointmentDateAndStatus(Date appointmentDate, int status);
    void appointmentReminder(AutoSchedulerEmailNotifierDTO notifierDTO) throws MessagingException;
    ApiResponse<Appointment> cancelAppointment(UserPrincipal principal,Long appointmentId);
}
