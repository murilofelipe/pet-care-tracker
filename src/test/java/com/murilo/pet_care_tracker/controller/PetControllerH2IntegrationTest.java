package com.murilo.pet_care_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murilo.pet_care_tracker.dto.PetRequestDTO;
import com.murilo.pet_care_tracker.messaging.PetKafkaProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerH2IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // O Spring vai substituir o produtor real por um falso durante esse
    @MockBean
    private PetKafkaProducer petKafkaProducer;

    @Test
    @DisplayName("Deve retornar 201 CREATED ao cadastrar um pet válido via API")
    void deveCadastrarPetE_Retornar201() throws Exception {
        // Arrange
        PetRequestDTO request = new PetRequestDTO("Sky", "Cachorro", "Pastor", LocalDate.of(2021, 8, 20), 12.0);
        String jsonPayload = objectMapper.writeValueAsString(request);

        // Act & Assert
        mockMvc.perform(post("/api/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Sky"))
                .andExpect(jsonPath("$.especie").value("Cachorro"));
    }

    @Test
    @DisplayName("Deve retornar 400 BAD REQUEST se o nome for vazio")
    void deveRetornar400SeNomeInvalido() throws Exception {
        // Arrange
        PetRequestDTO request = new PetRequestDTO("", "Cachorro", "Pastor", LocalDate.of(2021, 8, 20), 12.0);
        String jsonPayload = objectMapper.writeValueAsString(request);

        // Act & Assert
        mockMvc.perform(post("/api/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isBadRequest());
    }
}