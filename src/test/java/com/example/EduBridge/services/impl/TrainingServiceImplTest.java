package com.example.EduBridge.services.impl;

import com.example.EduBridge.dto.TrainingDTO;
import com.example.EduBridge.models.Training;
import com.example.EduBridge.models.enums.TrainingStatus;
import com.example.EduBridge.repositories.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceImplTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TrainingServiceImpl trainingService;

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
        given(trainingRepository.existsById(1L)).willReturn(false);
        given(modelMapper.map(trainingDTO1, Training.class)).willReturn(training1);
        given(trainingRepository.save(training1)).willReturn(training1);
        given(modelMapper.map(training1, TrainingDTO.class)).willReturn(trainingDTO1);

        TrainingDTO result = trainingService.saveTraining(trainingDTO1);

        assertEquals(trainingDTO1.getId(), result.getId());
        verify(trainingRepository).save(training1);
    }

    @Test
    @DisplayName("Test update method")
    void testUpdate() {
        given(trainingRepository.existsById(1L)).willReturn(true);
        given(modelMapper.map(trainingDTO1, Training.class)).willReturn(training1);
        given(trainingRepository.save(training1)).willReturn(training1);
        given(modelMapper.map(training1, TrainingDTO.class)).willReturn(trainingDTO1);

        TrainingDTO result = trainingService.updateTraining(trainingDTO1);

        assertEquals(trainingDTO1.getId(), result.getId());
        verify(trainingRepository).save(training1);
    }

    @Test
    @DisplayName("Test delete method")
    void testDelete() {
        given(trainingRepository.existsById(1L)).willReturn(true);

        trainingService.deleteTraining(1L);

        verify(trainingRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test get method")
    void testGet() {
        given(trainingRepository.existsById(1L)).willReturn(true);
        given(trainingRepository.findById(1L)).willReturn(Optional.of(training1));
        given(modelMapper.map(training1, TrainingDTO.class)).willReturn(trainingDTO1);

        Optional<TrainingDTO> result = trainingService.getTraining(1L);

        assertEquals(trainingDTO1.getId(), result.get().getId());
    }

    @Test
    @DisplayName("Test get all method")
    void testGetAll() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Training> trainings = Arrays.asList(training1);
        Page<Training> trainingPage = new PageImpl<>(trainings, pageable, trainings.size());

        given(trainingRepository.findAll(pageable)).willReturn(trainingPage);
        given(modelMapper.map(training1, TrainingDTO.class)).willReturn(trainingDTO1);

        Page<TrainingDTO> result = trainingService.getAllTraining(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Java", result.getContent().get(0).getTitle());
    }

    @Test
    @DisplayName("Test search by title method")
    void testSearchTrainingByTitle() {
        String title = "Java";
        Pageable pageable = PageRequest.of(0, 5);
        List<Training> trainings = Arrays.asList(training1);
        Page<Training> trainingPage = new PageImpl<>(trainings, pageable, trainings.size());

        given(trainingRepository.findByTitleContainingIgnoreCase(title, pageable)).willReturn(trainingPage);
        given(modelMapper.map(training1, TrainingDTO.class)).willReturn(trainingDTO1);

        Page<TrainingDTO> result = trainingService.searchTrainingByTitle(title, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Java", result.getContent().get(0).getTitle());
        verify(trainingRepository).findByTitleContainingIgnoreCase(title, pageable);
    }
}
