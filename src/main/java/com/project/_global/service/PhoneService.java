package com.project._global.service;

import java.util.List;
import java.util.UUID;

import com.project._global.entity.Phone;

public interface PhoneService {
    Phone createDevice(Phone phone);

    Phone updateDevice(UUID id, Phone phone);

    Phone partialUpdate(UUID id, Phone phone);

    Phone getDeviceById(UUID id);

    List<Phone> getAllDevices();

    List<Phone> getAllDevicesByBrand(String brand);

    List<Phone> getAllDevicesByState(String state);

    void deleteDevice(UUID id);

}
