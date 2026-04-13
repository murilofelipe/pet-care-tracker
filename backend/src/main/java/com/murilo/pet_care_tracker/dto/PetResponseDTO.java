package com.murilo.pet_care_tracker.dto;

import java.time.LocalDate;

// DTO para retornar os dados (escondendo detalhes internos se necessário)
public record PetResponseDTO(
        Long id,
        String nome,
        String especie,
        String raca,
        LocalDate dataNascimento,
        Double pesoAtual) {
}