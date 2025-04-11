package com.project._global.application.DTO;

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
public class PhoneUpdateDTO {
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String brand;

    @Pattern(regexp = "AVAILABLE|IN_USE|INACTIVE")
    private String state;
}
