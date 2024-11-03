package com.example.EduBridge.services.impl;

import com.example.EduBridge.dto.LearnerDTO;
import com.example.EduBridge.exceptions.AlreadyExistsException;
import com.example.EduBridge.exceptions.DoesNotExistsException;
import com.example.EduBridge.models.Learner;
import com.example.EduBridge.repositories.LearnerRepository;
import com.example.EduBridge.services.LearnerService;
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
public class LearnerServiceImpl implements LearnerService {
    private final LearnerRepository learnerRepository;
    private final ModelMapper modelMapper;

    @Override
    public LearnerDTO saveLearner(LearnerDTO learnerDTO) {
        if (learnerRepository.existsById(learnerDTO.getId())) {
            throw new AlreadyExistsException("Learner with id " + learnerDTO.getId() + " already exists");
        }else {
            Learner learner = modelMapper.map(learnerDTO, Learner.class);
            learner = learnerRepository.save(learner);
            return modelMapper.map(learner, LearnerDTO.class);
        }
    }

    @Override
    public Optional<LearnerDTO> getLearner(Long id) {
        if (learnerRepository.existsById(id)) {
            Learner learner = learnerRepository.findById(id).get();
            return Optional.of(modelMapper.map(learner, LearnerDTO.class));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public LearnerDTO updateLearner(LearnerDTO learnerDTO) {
        if (learnerRepository.existsById(learnerDTO.getId())) {
            Learner learner = modelMapper.map(learnerDTO, Learner.class);
            learner = learnerRepository.save(learner);
            return modelMapper.map(learner, LearnerDTO.class);
        }else {
            throw new DoesNotExistsException("Learner with id " + learnerDTO.getId() + " does not exist");
        }
    }

    @Override
    public void deleteLearner(Long id) {
        if (learnerRepository.existsById(id)) {
            learnerRepository.deleteById(id);
        }else {
            throw new DoesNotExistsException("Learner with id " + id + " does not exist");
        }
    }

    @Override
    public List<LearnerDTO> getAllLearner() {
        List<Learner> learners = learnerRepository.findAll();
        return learners.stream()
                .map(learner -> modelMapper.map(learner, LearnerDTO.class))
                .collect(Collectors.toList());
    }
}
