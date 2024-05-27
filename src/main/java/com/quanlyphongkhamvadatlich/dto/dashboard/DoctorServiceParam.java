package com.quanlyphongkhamvadatlich.dto.dashboard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorServiceParam {
    @NotBlank(message = "Vui lòng nhập họ và tên")
    @Size(max = 255)
    private String username;
    @NotBlank(message = "Vui lòng nhập chuyên môn")
    @Size(max = 255)
    private String specialty;
    @Size(max = 255)
    private String diploma;
    @Size(max = 255)
    private String workplace;
    @Size(max = 2000)
    private String introduction;
}
