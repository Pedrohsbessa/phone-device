package com.project._global.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.project._global.domain.entity.Phone;
import com.project._global.domain.entity.PhoneState;
import com.project._global.infrastructure.repository.PhoneRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PhoneRepositoryTest {

    @Autowired
    private PhoneRepository repository;

    @Test
    void findByBrand_ShouldReturnPhones() {
        // Given
        Phone phone = Phone.builder()
                .name("Test Phone")
                .brand("TestBrand")
                .state(PhoneState.AVAILABLE)
                .build();
        repository.save(phone);

        // When
        Page<Phone> result = repository.findByBrand("TestBrand", PageRequest.of(0, 10));

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.getContent().get(0).getBrand()).isEqualTo("TestBrand");
    }

    @Test
    void findByState_ShouldReturnPhones() {
        // Given
        Phone phone = Phone.builder()
                .name("Test Phone")
                .brand("TestBrand")
                .state(PhoneState.IN_USE)
                .build();
        repository.save(phone);

        // When
        Page<Phone> result = repository.findByState(PhoneState.IN_USE, PageRequest.of(0, 10));

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.getContent().get(0).getState()).isEqualTo(PhoneState.IN_USE);
    }
}