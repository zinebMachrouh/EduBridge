package com.example.EduBridge.services.impl;

import com.example.EduBridge.dto.TrainerDTO;
import com.example.EduBridge.exceptions.AlreadyExistsException;
import com.example.EduBridge.exceptions.DoesNotExistsException;
import com.example.EduBridge.models.Trainer;
import com.example.EduBridge.repositories.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceImplTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    private Trainer trainer1;
    private TrainerDTO trainerDTO1;

    @BeforeEach
    public void init() {
        trainerDTO1 = TrainerDTO.builder()
                .id(1L)
                .fname("John")
                .lname("Doe")
                .email("john@gmail.com")
                .speciality("Java")
                .build();

        trainer1 = Trainer.builder()
                .id(1L)
                .fname("John")
                .lname("Doe")
                .email("john@gmail.com")
                .speciality("Java")
                .build();
    }

    @Test
    @DisplayName("Test save method - success")
    void testSaveTrainer() {
        given(trainerRepository.existsById(1L)).willReturn(false);
        given(modelMapper.map(trainerDTO1, Trainer.class)).willReturn(trainer1);
        given(trainerRepository.save(trainer1)).willReturn(trainer1);
        given(modelMapper.map(trainer1, TrainerDTO.class)).willReturn(trainerDTO1);

        TrainerDTO result = trainerService.saveTrainer(trainerDTO1);

        assertEquals(trainerDTO1.getId(), result.getId());
        verify(trainerRepository).save(trainer1);
    }

    @Test
    @DisplayName("Test save method - already exists")
    void testSaveTrainerAlreadyExists() {
        given(trainerRepository.existsById(1L)).willReturn(true);

        assertThrows(AlreadyExistsException.class, () -> trainerService.saveTrainer(trainerDTO1));
    }

    @Test
    @DisplayName("Test get method - exists")
    void testGetTrainer() {
        given(trainerRepository.existsById(1L)).willReturn(true);
        given(trainerRepository.findById(1L)).willReturn(Optional.of(trainer1));
        given(modelMapper.map(trainer1, TrainerDTO.class)).willReturn(trainerDTO1);

        Optional<TrainerDTO> result = trainerService.getTrainer(1L);

        assertEquals(trainerDTO1.getId(), result.get().getId());
    }

    @Test
    @DisplayName("Test get method - does not exist")
    void testGetTrainerDoesNotExist() {
        given(trainerRepository.existsById(1L)).willReturn(false);

        Optional<TrainerDTO> result = trainerService.getTrainer(1L);

        assertEquals(Optional.empty(), result);
    }

    @Test
    @DisplayName("Test update method - success")
    void testUpdateTrainer() {
        given(trainerRepository.existsById(1L)).willReturn(true);
        given(modelMapper.map(trainerDTO1, Trainer.class)).willReturn(trainer1);
        given(trainerRepository.save(trainer1)).willReturn(trainer1);
        given(modelMapper.map(trainer1, TrainerDTO.class)).willReturn(trainerDTO1);

        TrainerDTO result = trainerService.updateTrainer(trainerDTO1);

        assertEquals(trainerDTO1.getId(), result.getId());
        verify(trainerRepository).save(trainer1);
    }

    @Test
    @DisplayName("Test update method - does not exist")
    void testUpdateTrainerDoesNotExist() {
        given(trainerRepository.existsById(1L)).willReturn(false);

        assertThrows(DoesNotExistsException.class, () -> trainerService.updateTrainer(trainerDTO1));
    }

    @Test
    @DisplayName("Test delete method - success")
    void testDeleteTrainer() {
        given(trainerRepository.existsById(1L)).willReturn(true);

        trainerService.deleteTrainer(1L);

        verify(trainerRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test delete method - does not exist")
    void testDeleteTrainerDoesNotExist() {
        given(trainerRepository.existsById(1L)).willReturn(false);

        assertThrows(DoesNotExistsException.class, () -> trainerService.deleteTrainer(1L));
    }

    @Test
    @DisplayName("Test get all method")
    void testGetAllTrainers() {
        List<Trainer> trainers = Arrays.asList(trainer1);
        List<TrainerDTO> trainerDTOs = Arrays.asList(trainerDTO1);

        given(trainerRepository.findAll()).willReturn(trainers);
        given(modelMapper.map(trainer1, TrainerDTO.class)).willReturn(trainerDTO1);

        List<TrainerDTO> result = trainerService.getAllTrainer();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFname());
    }
}
