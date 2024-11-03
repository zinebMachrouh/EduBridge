package com.example.EduBridge.services;

import com.example.EduBridge.dto.TrainerDTO;

import java.util.List;
import java.util.Optional;

public interface TrainerService {
    TrainerDTO saveTrainer(TrainerDTO trainerDTO);
    Optional<TrainerDTO> getTrainer(Long id);
    TrainerDTO updateTrainer(TrainerDTO trainerDTO);
    void deleteTrainer(Long id);
    List<TrainerDTO> getAllTrainer();
}
