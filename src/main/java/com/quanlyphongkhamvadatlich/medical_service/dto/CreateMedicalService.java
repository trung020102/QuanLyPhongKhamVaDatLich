package com.quanlyphongkhamvadatlich.medical_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateMedicalService {
    @NotBlank
    @Size(max = 255)
    private String serviceName;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    @NotBlank
    @Size(max= 1000)
    private String description;
}
