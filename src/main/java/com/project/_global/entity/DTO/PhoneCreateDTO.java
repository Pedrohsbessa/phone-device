package com.project._global.entity.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneCreateDTO {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Brand is required")
    private String brand;
}
