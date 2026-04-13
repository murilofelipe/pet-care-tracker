package com.murilo.pet_care_tracker.controller;

import com.murilo.pet_care_tracker.dto.PetRequestDTO;
import com.murilo.pet_care_tracker.dto.PetResponseDTO;
import com.murilo.pet_care_tracker.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetResponseDTO> cadastrarPet(@RequestBody @Valid PetRequestDTO dto) {
        PetResponseDTO petSalvo = petService.salvarPet(dto);
        // Retorna status 201 (Created) quando o recurso é salvo com sucesso
        return ResponseEntity.status(HttpStatus.CREATED).body(petSalvo);
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDTO>> listarTodos() {
        List<PetResponseDTO> pets = petService.listarTodos();
        return ResponseEntity.ok(pets); // Retorna 200 (OK)
    }
}