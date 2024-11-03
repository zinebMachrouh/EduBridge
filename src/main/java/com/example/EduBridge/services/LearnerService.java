package com.example.EduBridge.services;

import com.example.EduBridge.dto.LearnerDTO;

import java.util.List;
import java.util.Optional;

public interface LearnerService {
    LearnerDTO saveLearner(LearnerDTO learnerDTO);
    Optional<LearnerDTO> getLearner(Long id);
    LearnerDTO updateLearner(LearnerDTO learnerDTO);
    void deleteLearner(Long id);
    List<LearnerDTO> getAllLearner();
}
