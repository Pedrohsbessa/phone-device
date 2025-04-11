package com.project._global.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Possible states of a phone device")
public enum PhoneState {
    @Schema(description = "Phone is available for use")
    AVAILABLE,

    @Schema(description = "Phone is currently in use")
    IN_USE,

    @Schema(description = "Phone is inactive/decommissioned")
    INACTIVE
}