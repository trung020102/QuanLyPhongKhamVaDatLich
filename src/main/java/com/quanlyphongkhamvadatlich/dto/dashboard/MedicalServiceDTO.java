package com.quanlyphongkhamvadatlich.dto.dashboard;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalServiceDTO {

    private Long id;
    private String serviceName;


    private String description;


    private BigDecimal price;
}
