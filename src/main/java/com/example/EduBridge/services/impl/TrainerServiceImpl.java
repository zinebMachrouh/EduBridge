package com.example.EduBridge.services.impl;

import com.example.EduBridge.dto.TrainerDTO;
import com.example.EduBridge.exceptions.AlreadyExistsException;
import com.example.EduBridge.exceptions.DoesNotExistsException;
import com.example.EduBridge.models.Trainer;
import com.example.EduBridge.repositories.TrainerRepository;
import com.example.EduBridge.services.TrainerService;
import lombok.AllArgsConstructor;
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
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final ModelMapper modelMapper;

    @Override
    public TrainerDTO saveTrainer(TrainerDTO trainerDTO) {
        if (trainerRepository.existsById(trainerDTO.getId())) {
            throw new AlreadyExistsException("Trainer with id " + trainerDTO.getId() + " already exists");
        }else {
            Trainer trainer = modelMapper.map(trainerDTO, Trainer.class);
            trainer = trainerRepository.save(trainer);
            return modelMapper.map(trainer, TrainerDTO.class);
        }
    }

    @Override
    public Optional<TrainerDTO> getTrainer(Long id) {
        if (trainerRepository.existsById(id)) {
            Trainer trainer = trainerRepository.findById(id).get();
            return Optional.of(modelMapper.map(trainer, TrainerDTO.class));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public TrainerDTO updateTrainer(TrainerDTO trainerDTO) {
        if (trainerRepository.existsById(trainerDTO.getId())) {
            Trainer trainer = modelMapper.map(trainerDTO, Trainer.class);
            trainer = trainerRepository.save(trainer);
            return modelMapper.map(trainer, TrainerDTO.class);
        }else {
            throw new DoesNotExistsException("Trainer with id " + trainerDTO.getId() + " does not exist");
        }
    }

    @Override
    public void deleteTrainer(Long id) {
        if (trainerRepository.existsById(id)) {
            trainerRepository.deleteById(id);
        }else {
            throw new DoesNotExistsException("Trainer with id " + id + " does not exist");
        }
    }

    @Override
    public List<TrainerDTO> getAllTrainer() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers.stream()
                .map(trainer -> modelMapper.map(trainer, TrainerDTO.class))
                .collect(Collectors.toList());
    }
}
