package com.project._global.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project._global.application.DTO.PhoneCreateDTO;
import com.project._global.domain.entity.Phone;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PhoneIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void fullPhoneLifecycle() throws Exception {
        // Create
        PhoneCreateDTO createDTO = PhoneCreateDTO.builder()
                .name("Integration Test Phone")
                .brand("Integration Brand")
                .build();

        MvcResult createResult = mockMvc.perform(post("/api/v1/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value(createDTO.getName()))
                .andReturn();

        // Extract ID from response
        String response = createResult.getResponse().getContentAsString();
        Phone createdPhone = objectMapper.readValue(
                objectMapper.readTree(response).get("data").toString(),
                Phone.class);

        // Update
        mockMvc.perform(patch("/api/v1/phones/" + createdPhone.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"state\":\"IN_USE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.state").value("IN_USE"));

        // Get
        mockMvc.perform(get("/api/v1/phones/" + createdPhone.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.state").value("IN_USE"));

        // Update to INACTIVE
        mockMvc.perform(patch("/api/v1/phones/" + createdPhone.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"state\":\"INACTIVE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.state").value("INACTIVE"));

        // Delete
        mockMvc.perform(delete("/api/v1/phones/" + createdPhone.getId()))
                .andExpect(status().isOk());
    }
}