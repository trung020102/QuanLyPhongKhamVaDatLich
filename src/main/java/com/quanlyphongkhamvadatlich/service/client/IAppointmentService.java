package com.quanlyphongkhamvadatlich.service.client;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO;

import com.quanlyphongkhamvadatlich.entity.Appointment;
import jakarta.mail.MessagingException;

public interface IAppointmentService {
    List<AutoSchedulerEmailNotifierDTO> findByAppointmentDateAndStatus(Date appointmentDate, int status);
    void appointmentReminder(AutoSchedulerEmailNotifierDTO notifierDTO) throws MessagingException;
   Appointment getAppointmentById(Long id);
}
