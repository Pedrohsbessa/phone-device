package com.project._global.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.project._global.common.exception.PhoneInUseException;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    @Id
    @GeneratedValue
    @Schema(description = "Unique identifier", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Column(nullable = false, length = 255)
    @NotBlank
    @Schema(description = "Name of the phone device", example = "iPhone 14")
    private String name;

    @Column(nullable = false, length = 255)
    @NotBlank
    @Schema(description = "Brand of the phone device", example = "Apple")
    private String brand;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Current state of the phone", example = "AVAILABLE")
    private PhoneState state = PhoneState.AVAILABLE;

    @CreationTimestamp
    @Column(name = "creation_time", nullable = false, updatable = false)
    @Schema(description = "Timestamp when the phone was created", example = "2025-04-11T10:15:30")
    private LocalDateTime creationTime;

    public boolean isInUse() {
        return PhoneState.IN_USE.equals(this.state);
    }

    public boolean canBeUpdated() {
        return !isInUse();
    }

    public boolean canBeDeleted() {
        return !isInUse();
    }

    public void validateStateTransition(PhoneState newState) {
        if (this.state == PhoneState.IN_USE && newState != PhoneState.INACTIVE) {
            throw new PhoneInUseException("Device in use can only transition to INACTIVE state");
        }
        if (this.state == PhoneState.INACTIVE && newState != PhoneState.AVAILABLE) {
            throw new PhoneInUseException("Inactive device can only transition to AVAILABLE state");
        }
    }
}