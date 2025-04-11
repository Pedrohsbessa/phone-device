package com.project._global.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.project._global.entity.Phone;
import com.project._global.entity.DTO.PhoneCreateDTO;
import com.project._global.repository.PhoneRepository;
import com.project._global.service.PhoneService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;

    @Override
    public Phone createDevice(PhoneCreateDTO phone) {
        Phone newPhone = new Phone();
        newPhone.setName(phone.getName());
        newPhone.setBrand(phone.getBrand());
        return phoneRepository.save(newPhone);
    }

}
