package com.project._global.application.service.serviceImpl;

import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project._global.application.DTO.PhoneCreateDTO;
import com.project._global.application.DTO.PhoneUpdateDTO;
import com.project._global.application.response.ApiResponse;
import com.project._global.application.service.PhoneService;
import com.project._global.common.exception.PhoneException;
import com.project._global.common.exception.PhoneInUseException;
import com.project._global.domain.entity.Phone;
import com.project._global.domain.entity.PhoneState;
import com.project._global.infrastructure.repository.PhoneRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository repository;

    @Override
    public ApiResponse<Phone> createDevice(PhoneCreateDTO dto) {
        log.info("Creating new phone device: {}", dto);
        validateCreateDTO(dto);

        Phone newPhone = Phone.builder()
                .name(dto.getName())
                .brand(dto.getBrand())
                .state(PhoneState.AVAILABLE)
                .build();

        Phone saved = repository.save(newPhone);
        log.info("Phone created successfully with id: {}", saved.getId());

        return buildApiResponse(true, saved, "Phone created successfully");
    }

    @Override
    public ApiResponse<Phone> updateDevice(UUID id, PhoneUpdateDTO dto) {
        log.info("Updating phone device with id: {}", id);

        Phone phone = findPhoneById(id);
        validateUpdateOperation(phone, dto);

        updatePhoneFields(phone, dto);
        Phone updated = repository.save(phone);

        return buildApiResponse(true, updated, "Phone updated successfully");
    }

    @Override
    public ApiResponse<Phone> partialUpdate(UUID id, PhoneUpdateDTO dto) {
        log.info("Partially updating phone device with id: {}", id);

        Phone phone = findPhoneById(id);
        validatePartialUpdateOperation(phone, dto);

        updatePhoneFieldsIfPresent(phone, dto);
        Phone updated = repository.save(phone);

        return buildApiResponse(true, updated, "Phone partially updated successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<Phone> getDeviceById(UUID id) {
        log.info("Fetching phone device with id: {}", id);
        Phone phone = findPhoneById(id);
        return buildApiResponse(true, phone, "Phone retrieved successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<Page<Phone>> getAllDevices(Pageable pageable) {
        log.info("Fetching all phones with pagination");
        Page<Phone> phones = repository.findAll(pageable);
        return buildApiResponse(true, phones, "Phones retrieved successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<Page<Phone>> getAllDevicesByBrand(String brand, Pageable pageable) {
        log.info("Fetching phones by brand: {}", brand);
        Page<Phone> phones = repository.findByBrand(brand, pageable);
        return buildApiResponse(true, phones, "Phones retrieved successfully by brand");
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<Page<Phone>> getAllDevicesByState(PhoneState state, Pageable pageable) {
        log.info("Fetching phones by state: {}", state);
        Page<Phone> phones = repository.findByState(state, pageable);
        return buildApiResponse(true, phones, "Phones retrieved successfully by state");
    }

    @Override
    public ApiResponse<Void> deleteDevice(UUID id) {
        log.info("Deleting phone device with id: {}", id);

        Phone phone = findPhoneById(id);
        validateDeleteOperation(phone);

        repository.deleteById(id);
        return buildApiResponse(true, null, "Phone deleted successfully");
    }

    // Helper methods
    private Phone findPhoneById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new PhoneException("Phone not found with id: " + id));
    }

    private void validateCreateDTO(PhoneCreateDTO dto) {
        Objects.requireNonNull(dto, "DTO cannot be null");
        Objects.requireNonNull(dto.getName(), "Name cannot be null");
        Objects.requireNonNull(dto.getBrand(), "Brand cannot be null");
    }

    private void validateUpdateOperation(Phone phone, PhoneUpdateDTO dto) {
        if (phone.isInUse()) {
            throw new PhoneInUseException("Cannot update name or brand of a device in use");
        }
    }

    private void validatePartialUpdateOperation(Phone phone, PhoneUpdateDTO dto) {
        if (phone.isInUse() && (dto.getName() != null || dto.getBrand() != null)) {
            throw new PhoneInUseException("Cannot update name or brand of a device in use");
        }
    }

    private void validateDeleteOperation(Phone phone) {
        if (phone.isInUse()) {
            throw new PhoneInUseException("Cannot delete a device that is in use");
        }
    }

    private void updatePhoneFields(Phone phone, PhoneUpdateDTO dto) {
        phone.setName(dto.getName());
        phone.setBrand(dto.getBrand());
        if (dto.getState() != null) {
            phone.setState(PhoneState.valueOf(dto.getState()));
        }
    }

    private void updatePhoneFieldsIfPresent(Phone phone, PhoneUpdateDTO dto) {
        if (dto.getName() != null) {
            phone.setName(dto.getName());
        }
        if (dto.getBrand() != null) {
            phone.setBrand(dto.getBrand());
        }
        if (dto.getState() != null) {
            phone.setState(PhoneState.valueOf(dto.getState()));
        }
    }

    private <T> ApiResponse<T> buildApiResponse(boolean success, T data, String message) {
        return ApiResponse.<T>builder()
                .success(success)
                .data(data)
                .message(message)
                .build();
    }
}