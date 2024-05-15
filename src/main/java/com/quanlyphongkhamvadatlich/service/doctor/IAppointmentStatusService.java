package com.quanlyphongkhamvadatlich.service.doctor;

import com.quanlyphongkhamvadatlich.entity.AppointmentStatus;

import java.util.List;

public interface IAppointmentStatusService {
    List<AppointmentStatus> findAll();
}
