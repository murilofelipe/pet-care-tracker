package com.murilo.pet_care_tracker.repository;

import com.murilo.pet_care_tracker.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // Só de estender o JpaRepository, já ganhamos o save(), findById(), findAll(),
    // deleteById(), etc.
}