package com.project._global.unit.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.project._global.application.DTO.PhoneCreateDTO;
import com.project._global.application.DTO.PhoneUpdateDTO;
import com.project._global.application.service.serviceImpl.PhoneServiceImpl;
import com.project._global.common.exception.PhoneInUseException;
import com.project._global.domain.entity.Phone;
import com.project._global.domain.entity.PhoneState;
import com.project._global.infrastructure.repository.PhoneRepository;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PhoneServiceImplTest {

    @Mock
    private PhoneRepository repository;

    @InjectMocks
    private PhoneServiceImpl service;

    private Phone phone;
    private PhoneCreateDTO createDTO;
    private PhoneUpdateDTO updateDTO;
    private UUID phoneId;

    @BeforeEach
    void setUp() {
        phoneId = UUID.randomUUID();
        phone = Phone.builder()
                .id(phoneId)
                .name("Test Phone")
                .brand("Test Brand")
                .state(PhoneState.AVAILABLE)
                .build();

        createDTO = PhoneCreateDTO.builder()
                .name("Test Phone")
                .brand("Test Brand")
                .build();

        updateDTO = PhoneUpdateDTO.builder()
                .name("Updated Phone")
                .brand("Updated Brand")
                .state("AVAILABLE")
                .build();
    }

    @Test
    void createDevice_Success() {
        when(repository.save(any(Phone.class))).thenReturn(phone);

        var result = service.createDevice(createDTO);

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getData()).isNotNull();
        assertThat(result.getData().getName()).isEqualTo(createDTO.getName());
    }

    @Test
    void createDevice_WithNullDTO_ThrowsException() {
        assertThatThrownBy(() -> service.createDevice(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void updateDevice_Success() {
        when(repository.findById(phoneId)).thenReturn(Optional.of(phone));
        when(repository.save(any(Phone.class))).thenReturn(phone);

        var result = service.updateDevice(phoneId, updateDTO);

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getData()).isNotNull();
    }

    @Test
    void updateDevice_PhoneInUse_ThrowsException() {
        phone.setState(PhoneState.IN_USE);
        when(repository.findById(phoneId)).thenReturn(Optional.of(phone));

        assertThatThrownBy(() -> service.updateDevice(phoneId, updateDTO))
                .isInstanceOf(PhoneInUseException.class);
    }

    @Test
    void deleteDevice_Success() {
        when(repository.findById(phoneId)).thenReturn(Optional.of(phone));

        var result = service.deleteDevice(phoneId);

        assertThat(result.isSuccess()).isTrue();
        verify(repository).deleteById(phoneId);
    }

    @Test
    void deleteDevice_PhoneInUse_ThrowsException() {
        phone.setState(PhoneState.IN_USE);
        when(repository.findById(phoneId)).thenReturn(Optional.of(phone));

        assertThatThrownBy(() -> service.deleteDevice(phoneId))
                .isInstanceOf(PhoneInUseException.class);
    }

    @Test
    void getAllDevices_Success() {
        Page<Phone> phonePage = new PageImpl<>(List.of(phone));
        when(repository.findAll(any(PageRequest.class))).thenReturn(phonePage);

        var result = service.getAllDevices(PageRequest.of(0, 10));

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getData().getContent()).hasSize(1);
    }
}