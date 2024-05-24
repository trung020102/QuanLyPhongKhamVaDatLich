package com.quanlyphongkhamvadatlich.service.doctor;

import com.quanlyphongkhamvadatlich.entity.Appointment;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IAppointmentService {



    List<Appointment> fillAll();

    List<Appointment> findAllOrderByAppointmentShiftAndOrderNumber();
   Page<Appointment> findPage(int pageNumber);

    Optional<Appointment> findById(Long id);


  // Page<List<Appointment>> findPageWithKeyword(int pageNumber, Date date);
    //List<Appointment> findByAppointmentDate(Date date);
    Page<Appointment> findByAppointmentDate(Date appointmentDate, int pageNumber);
    Appointment createAppointment(Appointment appointment);

    Appointment updateAppointment(Long id, Appointment appointment);

    void updateAppointmentStatus(Long appointmentId, Long appointmentStatusId);

    void deleteAppointment(Long id);
}
