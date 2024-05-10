package com.quanlyphongkhamvadatlich.dto.dashboard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorServiceParam {
    @NotBlank
    @Size(max = 255)
    private String username;
    @NotBlank
    @Size(max = 255)
    private String avatar;
    @NotBlank
    @Size(max = 255)
    private String specialty;
    @NotBlank
    @Size
    private String diploma;
    @NotBlank
    @Size(max = 255)
    private String workplace;
    @NotBlank
    @Size(max = 2000)
    private String introduction;
}
