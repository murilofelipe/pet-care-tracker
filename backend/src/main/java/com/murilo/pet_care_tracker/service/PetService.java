package com.murilo.pet_care_tracker.service;

import com.murilo.pet_care_tracker.dto.PetRequestDTO;
import com.murilo.pet_care_tracker.dto.PetResponseDTO;
import com.murilo.pet_care_tracker.messaging.PetKafkaProducer;
import com.murilo.pet_care_tracker.model.Pet;
import com.murilo.pet_care_tracker.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok injeta o PetRepository automaticamente pelo construtor
public class PetService {

    private final PetRepository petRepository;
    private final PetKafkaProducer kafkaProducer; // Injetando o nosso produtor

    @Transactional
    public PetResponseDTO salvarPet(PetRequestDTO dto) {
        // Converter DTO para Entidade (Em projetos grandes, usariamos MapStruct)
        Pet pet = new Pet();
        pet.setNome(dto.nome());
        pet.setEspecie(dto.especie());
        pet.setRaca(dto.raca());
        pet.setDataNascimento(dto.dataNascimento());
        pet.setPesoAtual(dto.pesoAtual());

        pet = petRepository.save(pet);
        PetResponseDTO response = converterParaResponseDTO(pet);

        // Disparar evento no Kafka aqui! Ex:
        kafkaProducer.enviarEventoPetCriado(response);

        return response;
    }

    public List<PetResponseDTO> listarTodos() {
        return petRepository.findAll().stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    // Método auxiliar de conversão
    private PetResponseDTO converterParaResponseDTO(Pet pet) {
        return new PetResponseDTO(
                pet.getId(), pet.getNome(), pet.getEspecie(),
                pet.getRaca(), pet.getDataNascimento(), pet.getPesoAtual());
    }
}