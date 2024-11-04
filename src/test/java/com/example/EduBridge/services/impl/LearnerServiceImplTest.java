package com.example.EduBridge.services.impl;

import com.example.EduBridge.dto.LearnerDTO;
import com.example.EduBridge.models.Learner;
import com.example.EduBridge.repositories.LearnerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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
public class LearnerServiceImplTest {
    @Mock
    private LearnerRepository learnerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LearnerServiceImpl learnerService;

    private Learner learner;
    private LearnerDTO learnerDTO;

    @BeforeEach
    public void init() {
        learnerDTO = LearnerDTO.builder()
                .id(1L)
                .fname("John")
                .lname("Doe")
                .email("john@gmail.com")
                .build();

        learner = Learner.builder()
                .id(1L)
                .fname("John")
                .lname("Doe")
                .email("john@gmail.com")
                .build();
    }

    @Test
    @DisplayName("test save learner")
    public void testSaveLearner() {
        given(learnerRepository.existsById(learnerDTO.getId())).willReturn(false);
        given(modelMapper.map(learnerDTO, Learner.class)).willReturn(learner);
        given(learnerRepository.save(learner)).willReturn(learner);
        given(modelMapper.map(learner, LearnerDTO.class)).willReturn(learnerDTO);

        LearnerDTO savedLearner = learnerService.saveLearner(learnerDTO);

        assertEquals(learnerDTO, savedLearner);
        verify(learnerRepository).existsById(learnerDTO.getId());
        verify(modelMapper).map(learnerDTO, Learner.class);
        verify(learnerRepository).save(learner);
        verify(modelMapper).map(learner, LearnerDTO.class);
    }

    @Test
    @DisplayName("test get learner")
    public void testGetLearner() {
        given(learnerRepository.existsById(learnerDTO.getId())).willReturn(true);
        given(learnerRepository.findById(learnerDTO.getId())).willReturn(Optional.of(learner));
        given(modelMapper.map(learner, LearnerDTO.class)).willReturn(learnerDTO);

        Optional<LearnerDTO> result = learnerService.getLearner(1L);

        assertEquals(Optional.of(learnerDTO), result);

        verify(learnerRepository).existsById(learnerDTO.getId());
        verify(learnerRepository).findById(learnerDTO.getId());
        verify(modelMapper).map(learner, LearnerDTO.class);
    }

    @Test
    @DisplayName("test update learner")
    public void testUpdateLearner() {
        given(learnerRepository.existsById(learnerDTO.getId())).willReturn(true);
        given(modelMapper.map(learnerDTO, Learner.class)).willReturn(learner);
        given(learnerRepository.save(learner)).willReturn(learner);
        given(modelMapper.map(learner, LearnerDTO.class)).willReturn(learnerDTO);

        LearnerDTO updatedLearner = learnerService.updateLearner(learnerDTO);

        assertEquals(learnerDTO, updatedLearner);
        verify(learnerRepository).existsById(learnerDTO.getId());
        verify(modelMapper).map(learnerDTO, Learner.class);
        verify(learnerRepository).save(learner);
        verify(modelMapper).map(learner, LearnerDTO.class);
    }

    @Test
    @DisplayName("test delete learner")
    public void testDeleteLearner() {
        given(learnerRepository.existsById(learnerDTO.getId())).willReturn(true);

        learnerService.deleteLearner(1L);

        verify(learnerRepository).existsById(learnerDTO.getId());
        verify(learnerRepository).deleteById(learnerDTO.getId());
    }

    @Test
    @DisplayName("test get all learners")
    public void testGetAllLearners() {
        List<Learner> learners = Arrays.asList(learner);
        given(learnerRepository.findAll()).willReturn(learners);
        given(modelMapper.map(learner, LearnerDTO.class)).willReturn(learnerDTO);

        List<LearnerDTO> learnerDTOs = learnerService.getAllLearner();

        assertEquals(1, learnerDTOs.size());
        verify(learnerRepository).findAll();
        verify(modelMapper).map(learner, LearnerDTO.class);
    }
}
