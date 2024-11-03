package com.example.EduBridge.services.impl;

import com.example.EduBridge.dto.TrainingDTO;
import com.example.EduBridge.exceptions.AlreadyExistsException;
import com.example.EduBridge.exceptions.DoesNotExistsException;
import com.example.EduBridge.models.Training;
import com.example.EduBridge.repositories.TrainingRepository;
import com.example.EduBridge.services.TrainingService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (trainingRepository.existsById(trainingDTO.getId())) {
            Training training = modelMapper.map(trainingDTO, Training.class);
            training = trainingRepository.save(training);
            return modelMapper.map(training, TrainingDTO.class);
        }else{
            throw new DoesNotExistsException("Training with id " + trainingDTO.getId() + " does not exist");
        }
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
    public List<TrainingDTO> getAllTraining() {
        List<Training> trainings = trainingRepository.findAll();
        return trainings.stream()
                .map(training -> modelMapper.map(training, TrainingDTO.class))
                .collect(Collectors.toList());
    }
}
