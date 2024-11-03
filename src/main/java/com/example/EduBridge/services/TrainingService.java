package com.example.EduBridge.services;

import com.example.EduBridge.dto.TrainingDTO;

import java.util.List;
import java.util.Optional;

public interface TrainingService {
    TrainingDTO saveTraining(TrainingDTO trainingDTO);
    Optional<TrainingDTO> getTraining(Long id);
    TrainingDTO updateTraining(TrainingDTO trainingDTO);
    void deleteTraining(Long id);
    List<TrainingDTO> getAllTraining();
}
