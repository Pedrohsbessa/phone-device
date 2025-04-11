package com.project._global.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project._global.application.DTO.PhoneCreateDTO;
import com.project._global.application.DTO.PhoneUpdateDTO;
import com.project._global.application.response.ApiResponse;
import com.project._global.application.service.PhoneService;
import com.project._global.domain.entity.Phone;
import com.project._global.domain.entity.PhoneState;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/api/v1/phones")
@RequiredArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;

    @PostMapping
    public ResponseEntity<ApiResponse<Phone>> createPhone(@Valid @RequestBody PhoneCreateDTO dto) {
        log.info("Creating new phone with data: {}", dto);
        ApiResponse<Phone> response = phoneService.createDevice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Phone>> updatePhone(
            @PathVariable UUID id,
            @Valid @RequestBody PhoneUpdateDTO dto) {
        log.info("Updating phone with id: {} and data: {}", id, dto);
        ApiResponse<Phone> response = phoneService.updateDevice(id, dto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Phone>> partialUpdatePhone(
            @PathVariable UUID id,
            @Valid @RequestBody PhoneUpdateDTO dto) {
        log.info("Partially updating phone with id: {} and data: {}", id, dto);
        ApiResponse<Phone> response = phoneService.partialUpdate(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Phone>> getPhoneById(@PathVariable UUID id) {
        log.info("Fetching phone with id: {}", id);
        ApiResponse<Phone> response = phoneService.getDeviceById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Phone>>> getAllPhones(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) PhoneState state,
            Pageable pageable) {
        log.info("Fetching phones with filters - brand: {}, state: {}", brand, state);

        ApiResponse<Page<Phone>> response;
        if (brand != null) {
            response = phoneService.getAllDevicesByBrand(brand, pageable);
        } else if (state != null) {
            response = phoneService.getAllDevicesByState(state, pageable);
        } else {
            response = phoneService.getAllDevices(pageable);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePhone(@PathVariable UUID id) {
        log.info("Deleting phone with id: {}", id);
        ApiResponse<Void> response = phoneService.deleteDevice(id);
        return ResponseEntity.ok(response);
    }
}
