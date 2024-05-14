package com.quanlyphongkhamvadatlich.service;

import com.quanlyphongkhamvadatlich.dto.client.AppointmentDTO;
import com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.entity.Status;
import com.quanlyphongkhamvadatlich.repository.AppointmentRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

//    public List<Appointment> ListOfAppointments(Date appointmentDate){return appointmentRepository.findByAppointmentDate(appointmentDate);}
    public void sendAppointmentInfo(String receiver, Integer orderNumber, Long patientId,String name, String phone, Date appointmentDate, String appointmentShift) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariable("orderNumber", orderNumber);
        context.setVariable("patientId", patientId);
        context.setVariable("name", name);
        context.setVariable("phone", phone);
        context.setVariable("appointmentDate", appointmentDate);
        context.setVariable("appointmentShift", appointmentShift);

        String process = templateEngine.process("template-email/AppointmentInfo",context);
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(receiver);
        helper.setFrom(new InternetAddress("chaunhihue@gmail.com"));
        helper.setSubject("THÔNG TIN ĐẶT KHÁM TẠI NOVENA - LỊCH KHÁM NGÀY " +new SimpleDateFormat("dd-MM-yyyy").format(appointmentDate));
        helper.setText(process, true);

        javaMailSender.send(message);
    }
    public Optional<Appointment> getAppointmentById(Long id){return appointmentRepository.findById(id);}
    public List<Appointment> getAppointmentByDateAndShift(Date appointmentDate, String appointmentShift){
        return appointmentRepository.findByAppointmentDateAndAppointmentShift(appointmentDate,appointmentShift);
    }
    public List<DisableAppointmentDTO> getAllDisableAppointments(){
        return appointmentRepository.getAllDisableAppointment();
    }
    public Appointment bookAppointment(AppointmentDTO booking, Optional<Patient> patientOptional, int statusId) throws Exception {

        List<Appointment> appointments = getAppointmentByDateAndShift(booking.getAppointmentDate(),booking.getAppointmentShift());
        int countAppointment = appointments.size();

        int orderNumber;
        if(countAppointment == 0){
            orderNumber = 0;
        }
        orderNumber = countAppointment + 1;

        if(!patientOptional.isPresent()) {
            return null;
        }
        Patient patient = patientOptional.get();
        Appointment appointment = Appointment
                .builder()
                .appointmentDate(booking.getAppointmentDate())
                .appointmentShift(booking.getAppointmentShift())
                .symptom(booking.getSymptom())
                .patient(patient)
                .status(new Status(statusId,"Chưa khám"))
                .orderNumber(orderNumber)
                .build();

//        appointment.setOrderNumber(countAppointment++);
        return appointmentRepository.save(appointment);
    }
}
