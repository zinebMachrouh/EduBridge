package com.example.EduBridge.services;

import com.example.EduBridge.dto.TrainingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TrainingService {
    TrainingDTO saveTraining(TrainingDTO trainingDTO);
    Optional<TrainingDTO> getTraining(Long id);
    TrainingDTO updateTraining(TrainingDTO trainingDTO);
    void deleteTraining(Long id);
    Page<TrainingDTO> getAllTraining(Pageable pageable);
    Page<TrainingDTO> searchTrainingByTitle(String title, Pageable pageable);
}
