package com.quanlyphongkhamvadatlich.dto.client;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDTO {
    @Id
    private  Long patientId;
    public PatientDTO(Long patientId){
        this.patientId = patientId;
    }

    @NotBlank(message = "Tên bệnh nhân không được để trống")
    private String name;

    @NotBlank(message = "Số điện thoại không được để trống")
//    @Size(min = 10, max = 10, message = "Số điện thoại phải có 10 chữ số")
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_of_birth;

    private Boolean gender;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotBlank(message = "Số CMND/CCCD không được để trống")
    private String citizen_number;

    private String career;

    private String insurance_number;


}
