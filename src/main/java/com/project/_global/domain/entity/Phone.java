package com.project._global.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

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
    private UUID id;

    @Column(nullable = false, length = 255)
    @NotBlank
    private String name;

    @Column(nullable = false, length = 255)
    @NotBlank
    private String brand;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneState state = PhoneState.AVAILABLE;

    @CreationTimestamp
    @Column(name = "creation_time", nullable = false, updatable = false)
    private LocalDateTime creationTime;

    public boolean isInUse() {
        return PhoneState.IN_USE.equals(this.state);
    }

}