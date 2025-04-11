package com.project._global.application.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project._global.application.DTO.PhoneCreateDTO;
import com.project._global.application.DTO.PhoneUpdateDTO;
import com.project._global.application.response.ApiResponse;
import com.project._global.domain.entity.Phone;
import com.project._global.domain.entity.PhoneState;

public interface PhoneService {
    ApiResponse<Phone> createDevice(PhoneCreateDTO phone);

    ApiResponse<Phone> updateDevice(UUID id, PhoneUpdateDTO dto);

    ApiResponse<Phone> partialUpdate(UUID id, PhoneUpdateDTO dto);

    ApiResponse<Phone> getDeviceById(UUID id);

    ApiResponse<Page<Phone>> getAllDevices(Pageable pageable);

    ApiResponse<Page<Phone>> getAllDevicesByBrand(String brand, Pageable pageable);

    ApiResponse<Page<Phone>> getAllDevicesByState(PhoneState state, Pageable pageable);

    ApiResponse<Void> deleteDevice(UUID id);

}
