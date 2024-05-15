package com.quanlyphongkhamvadatlich.service.doctor.impl;


import com.quanlyphongkhamvadatlich.entity.AppointmentStatus;
import com.quanlyphongkhamvadatlich.repository.AppointmentStatusRepository;
import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentStatusServiceImpl implements IAppointmentStatusService {

    @Autowired
    AppointmentStatusRepository appointmentStatusRepository;

    @Override
    public List<AppointmentStatus> findAll() {
        return appointmentStatusRepository.findAll();
    }
}
