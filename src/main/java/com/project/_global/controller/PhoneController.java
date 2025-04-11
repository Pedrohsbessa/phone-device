package com.project._global.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project._global.application.DTO.PhoneCreateDTO;
import com.project._global.application.DTO.PhoneUpdateDTO;
import com.project._global.application.service.PhoneService;
import com.project._global.domain.entity.Phone;
import com.project._global.domain.entity.PhoneState;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Phone Management", description = "Operations for managing phone devices")
public class PhoneController {

    private final PhoneService phoneService;

    @Operation(summary = "Create a new phone device", description = "Creates a new phone device entry with the provided details")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Phone created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<com.project._global.application.response.ApiResponse<Phone>> createPhone(
            @Parameter(description = "Phone data to create", required = true) @Valid @RequestBody PhoneCreateDTO dto) {
        log.info("Creating new phone with data: {}", dto);
        com.project._global.application.response.ApiResponse<Phone> response = phoneService.createDevice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update a phone completely", description = "Updates all fields of an existing phone")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Phone updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Phone not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Cannot update phone that is in use")
    })
    @PutMapping("/{id}")
    public ResponseEntity<com.project._global.application.response.ApiResponse<Phone>> updatePhone(
            @Parameter(description = "Phone ID", required = true) @PathVariable UUID id,
            @Parameter(description = "Updated phone data", required = true) @Valid @RequestBody PhoneUpdateDTO dto) {
        log.info("Updating phone with id: {} and data: {}", id, dto);
        com.project._global.application.response.ApiResponse<Phone> response = phoneService.updateDevice(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a phone partially", description = "Updates only the provided fields of an existing phone")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Phone updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Phone not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Cannot update phone that is in use")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<com.project._global.application.response.ApiResponse<Phone>> partialUpdatePhone(
            @Parameter(description = "Phone ID", required = true) @PathVariable UUID id,
            @Parameter(description = "Partial phone data to update", required = true) @Valid @RequestBody PhoneUpdateDTO dto) {
        log.info("Partially updating phone with id: {} and data: {}", id, dto);
        com.project._global.application.response.ApiResponse<Phone> response = phoneService.partialUpdate(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get phone by ID", description = "Retrieves a specific phone by its ID")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Phone found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Phone not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<com.project._global.application.response.ApiResponse<Phone>> getPhoneById(
            @Parameter(description = "Phone ID", required = true) @PathVariable UUID id) {
        log.info("Fetching phone with id: {}", id);
        com.project._global.application.response.ApiResponse<Phone> response = phoneService.getDeviceById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all phones", description = "Retrieves all phones with optional filtering by brand or state")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "List of phones retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<com.project._global.application.response.ApiResponse<Page<Phone>>> getAllPhones(
            @Parameter(description = "Filter by brand (optional)") @RequestParam(required = false) String brand,
            @Parameter(description = "Filter by state (optional)") @RequestParam(required = false) PhoneState state,
            @Parameter(description = "Pagination and sorting parameters") Pageable pageable) {
        log.info("Fetching phones with filters - brand: {}, state: {}", brand, state);

        com.project._global.application.response.ApiResponse<Page<Phone>> response;
        if (brand != null) {
            response = phoneService.getAllDevicesByBrand(brand, pageable);
        } else if (state != null) {
            response = phoneService.getAllDevicesByState(state, pageable);
        } else {
            response = phoneService.getAllDevices(pageable);
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a phone", description = "Deletes a phone by its ID if not in use")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Phone deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Phone not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Cannot delete a phone that is in use")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<com.project._global.application.response.ApiResponse<Void>> deletePhone(
            @Parameter(description = "Phone ID", required = true) @PathVariable UUID id) {
        log.info("Deleting phone with id: {}", id);
        com.project._global.application.response.ApiResponse<Void> response = phoneService.deleteDevice(id);
        return ResponseEntity.ok(response);
    }
}