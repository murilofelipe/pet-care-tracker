package com.murilo.pet_care_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

// DTO para receber os dados de criação/atualização (com validação do Bean Validation)
public record PetRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,

        @NotBlank(message = "A espécie é obrigatória") String especie,

        String raca,
        LocalDate dataNascimento,

        @Positive(message = "O peso deve ser maior que zero") Double pesoAtual) {
}
