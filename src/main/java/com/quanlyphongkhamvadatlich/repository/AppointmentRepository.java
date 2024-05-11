package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public Optional<Appointment> getAppointmentById(Long id);

    public List<Appointment> findByAppointmentDateAndAppointmentShift(Date appointmentDate, String appointmentShift);


    @Query(value = "SELECT new com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO(a.appointmentDate) FROM Appointment a GROUP BY a.appointmentDate HAVING COUNT(a.id) >= 6")
    public List<DisableAppointmentDTO> getAllDisableAppointment();
}
