package com.murilo.pet_care_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pets")
@Data // Lombok: Gera Getters, Setters, toString, equals e hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String especie; // No futuro, podemos refatorar para um Enum (CACHORRO, GATO, COELHO)

    @Column(length = 50)
    private String raca;

    private LocalDate dataNascimento;

    private Double pesoAtual;
}