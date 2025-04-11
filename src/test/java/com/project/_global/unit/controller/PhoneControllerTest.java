package com.project._global.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project._global.application.DTO.PhoneCreateDTO;
import com.project._global.application.DTO.PhoneUpdateDTO;
import com.project._global.application.response.ApiResponse;
import com.project._global.application.service.PhoneService;
import com.project._global.controller.PhoneController;
import com.project._global.domain.entity.Phone;
import com.project._global.domain.entity.PhoneState;

@WebMvcTest(PhoneController.class)
class PhoneControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private PhoneService service;

        @Autowired
        private ObjectMapper objectMapper;

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
        void createPhone_Success() throws Exception {
                when(service.createDevice(any(PhoneCreateDTO.class)))
                                .thenReturn(ApiResponse.<Phone>builder()
                                                .success(true)
                                                .data(phone)
                                                .message("Phone created successfully")
                                                .build());

                mockMvc.perform(post("/api/v1/phones")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDTO)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.name").value(phone.getName()));
        }

        @Test
        void createPhone_InvalidInput() throws Exception {
                createDTO.setName(""); // Invalid name

                mockMvc.perform(post("/api/v1/phones")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDTO)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void updatePhone_Success() throws Exception {
                when(service.updateDevice(any(UUID.class), any(PhoneUpdateDTO.class)))
                                .thenReturn(ApiResponse.<Phone>builder()
                                                .success(true)
                                                .data(phone)
                                                .message("Phone updated successfully")
                                                .build());

                mockMvc.perform(put("/api/v1/phones/" + phoneId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateDTO)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true));
        }

        @Test
        void getPhoneById_Success() throws Exception {
                when(service.getDeviceById(any(UUID.class)))
                                .thenReturn(ApiResponse.<Phone>builder()
                                                .success(true)
                                                .data(phone)
                                                .message("Phone retrieved successfully")
                                                .build());

                mockMvc.perform(get("/api/v1/phones/" + phoneId))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.id").value(phoneId.toString()));
        }

        @Test
        void getAllPhones_NoFilters_Success() throws Exception {
                Page<Phone> phonePage = new PageImpl<>(List.of(phone));
                when(service.getAllDevices(any(Pageable.class)))
                                .thenReturn(ApiResponse.<Page<Phone>>builder()
                                                .success(true)
                                                .data(phonePage)
                                                .message("Phones retrieved successfully")
                                                .build());

                mockMvc.perform(get("/api/v1/phones"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.content[0].name").value(phone.getName()));
        }

        @Test
        void getAllPhones_FilterByBrand_Success() throws Exception {
                Page<Phone> phonePage = new PageImpl<>(List.of(phone));
                when(service.getAllDevicesByBrand(any(String.class), any(Pageable.class)))
                                .thenReturn(ApiResponse.<Page<Phone>>builder()
                                                .success(true)
                                                .data(phonePage)
                                                .message("Phones retrieved successfully")
                                                .build());

                mockMvc.perform(get("/api/v1/phones")
                                .param("brand", "Test Brand"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.content[0].brand").value(phone.getBrand()));
        }

        @Test
        void getAllPhones_FilterByState_Success() throws Exception {
                Page<Phone> phonePage = new PageImpl<>(List.of(phone));
                when(service.getAllDevicesByState(any(PhoneState.class), any(Pageable.class)))
                                .thenReturn(ApiResponse.<Page<Phone>>builder()
                                                .success(true)
                                                .data(phonePage)
                                                .message("Phones retrieved successfully")
                                                .build());

                mockMvc.perform(get("/api/v1/phones")
                                .param("state", "AVAILABLE"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.content[0].state").value("AVAILABLE"));
        }

        @Test
        void getAllPhones_WithPagination_Success() throws Exception {
                // Given
                Page<Phone> phonePage = new PageImpl<>(List.of(phone), PageRequest.of(0, 10), 1);
                when(service.getAllDevices(any(Pageable.class)))
                                .thenReturn(ApiResponse.<Page<Phone>>builder()
                                                .success(true)
                                                .data(phonePage)
                                                .message("Phones retrieved successfully")
                                                .build());

                // When/Then
                mockMvc.perform(get("/api/v1/phones")
                                .param("page", "0")
                                .param("size", "10")
                                .param("sort", "name,desc"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.content[0].name").value(phone.getName()))
                                .andExpect(jsonPath("$.data.pageable.pageNumber").value(0))
                                .andExpect(jsonPath("$.data.pageable.pageSize").value(10))
                                .andExpect(jsonPath("$.data.totalElements").value(1));
        }

        @Test
        void deletePhone_Success() throws Exception {
                when(service.deleteDevice(any(UUID.class)))
                                .thenReturn(ApiResponse.<Void>builder()
                                                .success(true)
                                                .message("Phone deleted successfully")
                                                .build());

                mockMvc.perform(delete("/api/v1/phones/" + phoneId))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true));
        }

        @Test
        void partialUpdatePhone_Success() throws Exception {
                when(service.partialUpdate(any(UUID.class), any(PhoneUpdateDTO.class)))
                                .thenReturn(ApiResponse.<Phone>builder()
                                                .success(true)
                                                .data(phone)
                                                .message("Phone partially updated successfully")
                                                .build());

                PhoneUpdateDTO partialUpdate = PhoneUpdateDTO.builder()
                                .state("IN_USE")
                                .build();

                mockMvc.perform(patch("/api/v1/phones/" + phoneId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(partialUpdate)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true));
        }

        @Test
        void getAllPhones_WithDifferentPageSizes_Success() throws Exception {
                // Given
                Page<Phone> phonePage = new PageImpl<>(List.of(phone), PageRequest.of(0, 5), 1);
                when(service.getAllDevices(any(Pageable.class)))
                                .thenReturn(ApiResponse.<Page<Phone>>builder()
                                                .success(true)
                                                .data(phonePage)
                                                .message("Phones retrieved successfully")
                                                .build());

                // When/Then
                mockMvc.perform(get("/api/v1/phones")
                                .param("page", "0")
                                .param("size", "5"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.content[0].name").value(phone.getName()))
                                .andExpect(jsonPath("$.data.pageable.pageSize").value(5));
        }

        @Test
        void getAllPhones_WithSorting_Success() throws Exception {
                // Given
                Page<Phone> phonePage = new PageImpl<>(List.of(phone),
                                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name")), 1);
                when(service.getAllDevices(any(Pageable.class)))
                                .thenReturn(ApiResponse.<Page<Phone>>builder()
                                                .success(true)
                                                .data(phonePage)
                                                .message("Phones retrieved successfully")
                                                .build());

                // When/Then
                mockMvc.perform(get("/api/v1/phones")
                                .param("sort", "name,desc"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.content[0].name").value(phone.getName()))
                                .andExpect(jsonPath("$.data.pageable.sort.sorted").value(true));
        }

        @Test
        void updatePhone_InvalidState_BadRequest() throws Exception {
                updateDTO.setState("INVALID_STATE");

                mockMvc.perform(put("/api/v1/phones/" + phoneId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateDTO)))
                                .andExpect(status().isBadRequest());
        }

}