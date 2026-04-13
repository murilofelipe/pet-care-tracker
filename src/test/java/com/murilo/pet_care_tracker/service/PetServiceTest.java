package com.murilo.pet_care_tracker.service;

import com.murilo.pet_care_tracker.dto.PetRequestDTO;
import com.murilo.pet_care_tracker.dto.PetResponseDTO;
import com.murilo.pet_care_tracker.messaging.PetKafkaProducer;
import com.murilo.pet_care_tracker.model.Pet;
import com.murilo.pet_care_tracker.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetKafkaProducer petKafkaProducer; // Precisamos fingir o Kafka no teste

    @InjectMocks
    private PetService petService;

    @Test
    @DisplayName("Deve salvar um pet com sucesso e retornar o DTO")
    void deveSalvarPetComSucesso() {
        PetRequestDTO requestDTO = new PetRequestDTO("Snow", "Cachorro", "Pastor", LocalDate.of(2020, 5, 10), 15.5);
        Pet petSalvoNoBanco = new Pet(1L, "Snow", "Cachorro", "Pastor", LocalDate.of(2020, 5, 10), 15.5);

        when(petRepository.save(any(Pet.class))).thenReturn(petSalvoNoBanco);

        // Não precisamos de 'when' para o KafkaProducer porque o método dele retorna
        // 'void'.
        // O Mockito automaticamente ignora chamadas void.

        PetResponseDTO response = petService.salvarPet(requestDTO);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Snow", response.nome());

        verify(petRepository, times(1)).save(any(Pet.class));

        // Podemos verificar se o Kafka foi de fato chamado durante o salvamento!
        verify(petKafkaProducer, times(1)).enviarEventoPetCriado(any(PetResponseDTO.class));
    }
}