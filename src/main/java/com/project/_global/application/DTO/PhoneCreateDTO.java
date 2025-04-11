package com.project._global.application.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object for creating a new phone")
public class PhoneCreateDTO {
    @Schema(description = "Name of the phone device", example = "Galaxy S21")
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must be alphanumeric")
    private String name;

    @Schema(description = "Brand of the phone device", example = "Samsung")
    @NotBlank(message = "Brand is required")
    @Size(min = 2, max = 255, message = "Brand must be between 2 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Brand must be alphanumeric")
    private String brand;
}