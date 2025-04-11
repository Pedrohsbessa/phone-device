package com.project._global.application.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneCreateDTO {
    @Size(max = 255)
    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 255)
    @NotBlank(message = "Brand is required")
    private String brand;
}
