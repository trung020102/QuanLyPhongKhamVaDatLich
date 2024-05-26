package com.quanlyphongkhamvadatlich.dto.dashboard;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorServiceResult {
    private Long id;
    private String doctor_name;
    private String specialty;
    private String diploma;
    private String workplace;
    private String introduction;
}
