package com.example.EduBridge.services.impl;

import com.example.EduBridge.EduBridgeApplication;
import com.example.EduBridge.dto.TrainingDTO;
import com.example.EduBridge.models.Training;
import com.example.EduBridge.models.enums.TrainingStatus;
import com.example.EduBridge.repositories.TrainingRepository;
import com.example.EduBridge.services.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = EduBridgeApplication.class)
@Transactional
public class TrainingServiceImplIntegrationTest {
    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private ModelMapper modelMapper;

    private Training training1;
    private TrainingDTO trainingDTO1;

    @BeforeEach
    public void init() {
        trainingDTO1 = TrainingDTO.builder()
                .id(1L)
                .title("Java")
                .level("beginner")
                .prerequisites("none")
                .minCapacity(10)
                .maxCapacity(20)
                .startDate(new Date())
                .endDate(new Date())
                .status(TrainingStatus.CANCELLED)
                .build();

        training1 = Training.builder()
                .id(1L)
                .title("Java")
                .level("beginner")
                .prerequisites("none")
                .minCapacity(10)
                .maxCapacity(20)
                .startDate(new Date())
                .endDate(new Date())
                .status(TrainingStatus.CANCELLED)
                .build();
    }

    @Test
    @DisplayName("Test create method")
    void testCreate() {
        TrainingDTO result = trainingService.saveTraining(trainingDTO1);
        Optional<Training> savedTraining = trainingRepository.findById(result.getId());

        assertTrue(savedTraining.isPresent());
        assertEquals(trainingDTO1.getTitle(), savedTraining.get().getTitle());
    }

    @Test
    @DisplayName("Test update method")
    void testUpdate() {
        Training savedTraining = trainingRepository.save(training1);

        trainingDTO1.setId(savedTraining.getId());
        trainingDTO1.setTitle("Advanced Java");

        TrainingDTO result = trainingService.updateTraining(trainingDTO1);

        Optional<Training> updatedTraining = trainingRepository.findById(result.getId());

        assertTrue(updatedTraining.isPresent());
        assertEquals("Advanced Java", updatedTraining.get().getTitle());
    }

    @Test
    @DisplayName("Test delete method")
    void testDelete() {
        Training savedTraining = trainingRepository.save(training1);

        trainingService.deleteTraining(savedTraining.getId());

        Optional<Training> deletedTraining = trainingRepository.findById(savedTraining.getId());
        assertTrue(!deletedTraining.isPresent());
    }

    @Test
    @DisplayName("Test get method")
    void testGet() {
        trainingRepository.save(training1);
        Optional<TrainingDTO> result = trainingService.getTraining(training1.getId());

        assertTrue(result.isPresent());
        assertEquals(training1.getTitle(), result.get().getTitle());
    }

    @Test
    @DisplayName("Test get all method")
    void testGetAll() {
        trainingRepository.save(training1);
        Pageable pageable = PageRequest.of(0, 5);
        Page<TrainingDTO> result = trainingService.getAllTraining(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Java", result.getContent().get(0).getTitle());
    }

    @Test
    @DisplayName("Test search by title method")
    void testSearchTrainingByTitle() {
        trainingRepository.save(training1);
        Pageable pageable = PageRequest.of(0, 5);
        Page<TrainingDTO> result = trainingService.searchTrainingByTitle("Java", pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Java", result.getContent().get(0).getTitle());
    }
}
