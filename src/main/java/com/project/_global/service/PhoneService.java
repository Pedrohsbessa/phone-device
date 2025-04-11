package com.project._global.service;

import java.util.List;
import java.util.UUID;

import com.project._global.entity.Phone;
import com.project._global.entity.DTO.PhoneCreateDTO;
import com.project._global.entity.DTO.PhoneUpdateDTO;

public interface PhoneService {
    Phone createDevice(PhoneCreateDTO phone);

    Phone updateDevice(UUID id, PhoneUpdateDTO dto);

    Phone partialUpdate(UUID id, PhoneUpdateDTO dto);

    Phone getDeviceById(UUID id);

    List<Phone> getAllDevices();

    List<Phone> getAllDevicesByBrand(String brand);

    List<Phone> getAllDevicesByState(String state);

    void deleteDevice(UUID id);

}
