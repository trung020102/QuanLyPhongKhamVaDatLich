package com.quanlyphongkhamvadatlich.dto.dashboard;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResister {
    @NotBlank(message = "Email không được bỏ trống")
    private String email;
    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private String password;
    @NotBlank(message = "Họ và tên không được bỏ trống")
    private String username;
    @NotBlank(message = "Chuyên ngành không đuộc bỏ trống")
    private String specialty;

}
