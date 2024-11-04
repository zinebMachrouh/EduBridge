package com.example.EduBridge.services.impl;

import com.example.EduBridge.dto.TrainingDTO;
import com.example.EduBridge.exceptions.AlreadyExistsException;
import com.example.EduBridge.exceptions.DoesNotExistsException;
import com.example.EduBridge.exceptions.TrainingStatusException;
import com.example.EduBridge.models.Training;
import com.example.EduBridge.repositories.TrainingRepository;
import com.example.EduBridge.services.TrainingService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
@AllArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final ModelMapper modelMapper;

    @Override
    public TrainingDTO saveTraining(TrainingDTO trainingDTO) {
        if (trainingRepository.existsById(trainingDTO.getId())) {
            throw new AlreadyExistsException("Training with id " + trainingDTO.getId() + " already exists");
        }else if (!isValidStatus(trainingDTO.getStatus().toString())) {
            throw new TrainingStatusException("Invalid status: " + trainingDTO.getStatus());
        }
        Training training = modelMapper.map(trainingDTO, Training.class);
        training = trainingRepository.save(training);
        return modelMapper.map(training, TrainingDTO.class);

    }

    @Override
    public Optional<TrainingDTO> getTraining(Long id) {
        if (trainingRepository.existsById(id)) {
            Training training = trainingRepository.findById(id).get();
            return Optional.of(modelMapper.map(training, TrainingDTO.class));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public TrainingDTO updateTraining(TrainingDTO trainingDTO) {
        if (!isValidStatus(trainingDTO.getStatus().toString())) {
            throw new TrainingStatusException("Invalid status: " + trainingDTO.getStatus());
        }

        if (!trainingRepository.existsById(trainingDTO.getId())) {
            throw new DoesNotExistsException("Training with id " + trainingDTO.getId() + " does not exist");
        }

        Training training = modelMapper.map(trainingDTO, Training.class);
        training = trainingRepository.save(training);
        return modelMapper.map(training, TrainingDTO.class);
    }

    @Override
    public void deleteTraining(Long id) {
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
        }else {
            throw new DoesNotExistsException("Training with id " + id + " does not exist");
        }
    }

    @Override
    public Page<TrainingDTO> getAllTraining(Pageable pageable) {
        Page<Training> trainingPage = trainingRepository.findAll(pageable);
        return trainingPage.map(training -> modelMapper.map(training, TrainingDTO.class));
    }

    @Override
    public Page<TrainingDTO> searchTrainingByTitle(String title, Pageable pageable) {
        Page<Training> trainingPage = trainingRepository.findByTitleContainingIgnoreCase(title, pageable);
        return trainingPage.map(training -> modelMapper.map(training, TrainingDTO.class));
    }

    private boolean isValidStatus(String status) {
        return status.equalsIgnoreCase("SCHEDULED") || status.equalsIgnoreCase("ONGOING") || status.equalsIgnoreCase("COMPLETED") || status.equalsIgnoreCase("CANCELLED");
    }
}
