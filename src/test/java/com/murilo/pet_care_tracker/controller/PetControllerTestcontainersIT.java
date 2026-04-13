package com.murilo.pet_care_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murilo.pet_care_tracker.dto.PetRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers // Ativa o gerenciador de contêineres do JUnit
class PetControllerTestcontainersIT {

    // SOBE UM POSTGRESQL REAL
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    // SOBE UM KAFKA REAL
    @Container
    @ServiceConnection
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("apache/kafka-native:3.8.0"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("[Testcontainers] Deve cadastrar pet no BD real e enviar msg pro Kafka real")
    void deveCadastrarPetE_Retornar201() throws Exception {

        // Garante que o Docker subiu tudo certinho antes de testar
        assert postgres.isRunning();
        assert kafka.isRunning();

        PetRequestDTO request = new PetRequestDTO("Aura", "Gato", "SDR", LocalDate.of(2022, 5, 5), 4.0);
        String jsonPayload = objectMapper.writeValueAsString(request);

        // O fluxo inteiro acontece: HTTP -> Service -> BD Real (Docker) -> Kafka Real
        // (Docker)
        mockMvc.perform(post("/api/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Aura"));
    }
}