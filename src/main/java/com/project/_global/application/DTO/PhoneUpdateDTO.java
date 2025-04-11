package com.project._global.application.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for updating a phone")
public class PhoneUpdateDTO {
    @Schema(description = "Updated name of the phone device", example = "Galaxy S22", nullable = true)
    @Size(max = 255)
    private String name;

    @Schema(description = "Updated brand of the phone device", example = "Samsung", nullable = true)
    @Size(max = 255)
    private String brand;

    @Schema(description = "Updated state of the phone device", example = "AVAILABLE", allowableValues = { "AVAILABLE",
            "IN_USE", "INACTIVE" }, nullable = true)
    @Pattern(regexp = "AVAILABLE|IN_USE|INACTIVE")
    private String state;
}
