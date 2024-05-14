package com.quanlyphongkhamvadatlich.service.doctor.impl;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.repository.AppointmentRepository;
import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> fillAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Page<Appointment> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return appointmentRepository.findAll(pageable);
    }



    @Override
    public Page<Appointment> findPageWithKeyword(int pageNumber, Date date) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return appointmentRepository.findByAppointmentDate(date, pageable);
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }


    @Override
    public List<Appointment> findByAppointmentDate(Date date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
//        // Kiểm tra xem cuộc hẹn có tồn tại không
//        Appointment existingAppointment = appointmentRepository.findById(id).orElse(null);
//
//        if (existingAppointment != null) {
//            // Cập nhật dữ liệu cuộc hẹn
//            existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
//            existingAppointment.setAppointmentShift(appointment.getAppointmentShift());
//            existingAppointment.setOrder_number(appointment.getOrder_number());
//            existingAppointment.setPatient(appointment.getPatient());
//            existingAppointment.setStatus(appointment.getStatus());
//
//            // Lưu và trả về cuộc hẹn đã cập nhật
//            return appointmentRepository.save(existingAppointment);
//        } else {
//            return null; // Xử lý trường hợp nếu cuộc hẹn không tồn tại
//        }

       /* Appointment existingAppointment = appointmentRepository.findById(appointment.getId()).orElse(null);

        if (existingAppointment != null) {
            // Update appointment data
            existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
            existingAppointment.setTimeSlot(appointment.getTimeSlot());
            existingAppointment.setOrderNumber(appointment.getOrderNumber());

            existingAppointment.setPatient(appointment.getPatient());
            existingAppointment.setAppointmentStatus(appointment.getAppointmentStatus());
            return appointmentRepository.save(existingAppointment);
        } else {
            return null; // Handle if appointment does not exist
        }*/
 return null;

    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
