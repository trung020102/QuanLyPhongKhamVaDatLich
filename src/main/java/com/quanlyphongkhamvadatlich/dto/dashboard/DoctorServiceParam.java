package com.quanlyphongkhamvadatlich.dto.dashboard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorServiceParam {
    @NotBlank
    @Size(max = 255)
    private String doctor_name;
    @NotNull
    @Size(max = 255)
    private String specialty;
    @NotNull
    @Size(max = 255)
    private String diploma;
    @NotNull
    @Size(max = 255)
    private String workplace;
    @NotNull
    @Size(max = 2000)
    private String introduction;
}
