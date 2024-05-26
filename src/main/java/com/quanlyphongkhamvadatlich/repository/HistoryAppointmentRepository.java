package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.dto.dashboard.HistoryAppointmentDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HistoryAppointmentRepository {
    @Query("SELECT new com.quanlyphongkhamvadatlich.dto.dashboard.HistoryAppointmentDTO(a.appointmentDate, a.appointmentShift, a.orderNumber, d.username, pr.diagnosis, a.status.id, pr.id)" +
            " FROM PatientRecord pr JOIN Appointment a ON pr.id = a.id" +
            " JOIN Doctor d ON pr.id = d.id" +
            " WHERE a.appointmentDate BETWEEN :startDate AND :endDate AND (a.status.id = 3 OR a.status.id = 4)")
    public List<HistoryAppointmentDTO> ListOfHistoryAppointmentByDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
