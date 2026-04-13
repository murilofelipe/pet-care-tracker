package com.murilo.pet_care_tracker.messaging;

import com.murilo.pet_care_tracker.dto.PetResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PetKafkaProducer {

    private final KafkaTemplate<String, PetResponseDTO> kafkaTemplate;
    private static final String TOPICO = "pet-cadastrado-topic";

    // O Spring Boot moderno entende automaticamente que deve injetar o
    // KafkaTemplate aqui
    public PetKafkaProducer(KafkaTemplate<String, PetResponseDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarEventoPetCriado(PetResponseDTO pet) {
        log.info("🚀 Enviando evento para o Kafka: Pet cadastrado - {}", pet.nome());
        kafkaTemplate.send(TOPICO, pet.id().toString(), pet);
    }
}