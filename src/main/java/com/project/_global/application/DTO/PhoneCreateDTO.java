package com.project._global.application.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must be alphanumeric")
    private String name;

    @NotBlank(message = "Brand is required")
    @Size(min = 2, max = 255, message = "Brand must be between 2 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Brand must be alphanumeric")
    private String brand;
}
