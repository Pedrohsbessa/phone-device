package com.project._global.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import com.project._global.common.exception.PhoneInUseException;
import com.project._global.domain.entity.Phone;
import com.project._global.domain.entity.PhoneState;

class PhoneStateTransitionTest {

    @Test
    void validateStateTransitions() {
        Phone phone = Phone.builder()
                .name("Test Phone")
                .brand("Test Brand")
                .state(PhoneState.AVAILABLE)
                .build();

        // AVAILABLE -> IN_USE
        phone.validateStateTransition(PhoneState.IN_USE);
        phone.setState(PhoneState.IN_USE);
        assertThat(phone.getState()).isEqualTo(PhoneState.IN_USE);

        // IN_USE -> INACTIVE (valid)
        phone.validateStateTransition(PhoneState.INACTIVE);
        phone.setState(PhoneState.INACTIVE);
        assertThat(phone.getState()).isEqualTo(PhoneState.INACTIVE);

        // INACTIVE -> AVAILABLE (valid)
        phone.validateStateTransition(PhoneState.AVAILABLE);
        phone.setState(PhoneState.AVAILABLE);
        assertThat(phone.getState()).isEqualTo(PhoneState.AVAILABLE);
    }

    @Test
    void invalidStateTransitions() {
        Phone phone = Phone.builder()
                .name("Test Phone")
                .brand("Test Brand")
                .state(PhoneState.IN_USE)
                .build();

        // IN_USE -> AVAILABLE (invalid)
        assertThatThrownBy(() -> phone.validateStateTransition(PhoneState.AVAILABLE))
                .isInstanceOf(PhoneInUseException.class)
                .hasMessageContaining("Device in use can only transition to INACTIVE state");

        // Set to INACTIVE
        phone.setState(PhoneState.INACTIVE);

        // INACTIVE -> IN_USE (invalid)
        assertThatThrownBy(() -> phone.validateStateTransition(PhoneState.IN_USE))
                .isInstanceOf(PhoneInUseException.class)
                .hasMessageContaining("Inactive device can only transition to AVAILABLE state");
    }
}