package com.quanlyphongkhamvadatlich.dto.dashboard;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HistoryAppointmentDTO {
    private Date appointmentDate;
    private Date appointmentShift;
    private int orderNumber;
    private String doctorName;
    private String diagnosis;
    private int statusId;

}
