package com.quanlyphongkhamvadatlich.service.client.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.entity.Patient;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.repository.AppointmentRepository;
import com.quanlyphongkhamvadatlich.service.client.IAppointmentService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public List<AutoSchedulerEmailNotifierDTO> findByAppointmentDateAndStatus(Date appointmentDate, int status) {
        return appointmentRepository.findByAppointmentDateAndStatusId(appointmentDate, status);
    }

    @Override
    public void appointmentReminder(AutoSchedulerEmailNotifierDTO notifierDTO) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariable("notifier", notifierDTO);
        String process = templateEngine.process("template-email/nhac-kham", context);
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(notifierDTO.getEmail());
        helper.setFrom(new InternetAddress("nhatminhle1402@gmail.com"));
        helper.setSubject("ĐỪNG QUÊN BẠN CÓ LỊCH KHÁM VÀO NGÀY MAI!");
        helper.setText(process, true);

        javaMailSender.send(message);
    }
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.getAppointmentById(id);
    }
}
