package com.example.EduBridge.services.impl;

import com.example.EduBridge.dto.ClassDTO;
import com.example.EduBridge.dto.TrainerDTO;
import com.example.EduBridge.dto.TrainingDTO;
import com.example.EduBridge.exceptions.AlreadyExistsException;
import com.example.EduBridge.models.Class;
import com.example.EduBridge.models.Trainer;
import com.example.EduBridge.models.Training;
import com.example.EduBridge.models.enums.TrainingStatus;
import com.example.EduBridge.repositories.ClassRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ClassServiceImplTest {

    @Mock
    private ClassRepository classRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClassServiceImpl classService;

    private Class classEntity;
    private ClassDTO classDTO;

    @BeforeEach
    public void init() {
        classDTO = ClassDTO.builder()
                .id(1L)
                .name("Java")
                .roomNumber(25)
                .training(
                        TrainingDTO.builder()
                                .id(1L)
                                .title("Java")
                                .level("beginner")
                                .prerequisites("none")
                                .minCapacity(10)
                                .maxCapacity(20)
                                .startDate(new Date())
                                .endDate(new Date())
                                .status(TrainingStatus.CANCELLED)
                                .build()
                )
                .trainer(
                        TrainerDTO.builder()
                                .id(1L)
                                .fname("John")
                                .lname("Doe")
                                .speciality("Java")
                                .build()
                )
                .build();

        classEntity = Class.builder()
                .id(1L)
                .name("Java")
                .roomNumber(25)
                .training(
                        Training.builder()
                                .id(1L)
                                .title("Java")
                                .level("beginner")
                                .prerequisites("none")
                                .minCapacity(10)
                                .maxCapacity(20)
                                .startDate(new Date())
                                .endDate(new Date())
                                .status(TrainingStatus.CANCELLED)
                                .build()
                )
                .trainer(
                        Trainer.builder()
                                .id(1L)
                                .fname("John")
                                .lname("Doe")
                                .speciality("Java")
                                .build()
                )
                .build();
    }

    @Test
    @DisplayName("Test save method - success")
    void testSaveClass() {
        given(classRepository.existsById(1L)).willReturn(false);
        given(modelMapper.map(classDTO, Class.class)).willReturn(classEntity);
        given(classRepository.save(classEntity)).willReturn(classEntity);
        given(modelMapper.map(classEntity, ClassDTO.class)).willReturn(classDTO);

        ClassDTO result = classService.saveClass(classDTO);

        assertEquals(classDTO.getId(), result.getId());
        verify(classRepository).save(classEntity);
    }

    @Test
    @DisplayName("Test save method - already exists")
    void testSaveClassAlreadyExists() {
        given(classRepository.existsById(1L)).willReturn(true);

        assertThrows(AlreadyExistsException.class, () -> classService.saveClass(classDTO));
    }

    @Test
    @DisplayName("Test get method")
    void testGetClass() {
        given(classRepository.existsById(1L)).willReturn(true);
        given(classRepository.findById(1L)).willReturn(Optional.ofNullable(classEntity));
        given(modelMapper.map(classEntity, ClassDTO.class)).willReturn(classDTO);

        Optional<ClassDTO> result = classService.getClass(1L);

        assertEquals(classDTO.getId(), result.get().getId());
    }

    @Test
    @DisplayName("Test delete method")
    void testDelete() {
        given(classRepository.existsById(1L)).willReturn(true);

        classService.deleteClass(1L);

        verify(classRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test update method")
    void testUpdate() {
        given(classRepository.existsById(1L)).willReturn(true);
        given(modelMapper.map(classDTO, Class.class)).willReturn(classEntity);
        given(classRepository.save(classEntity)).willReturn(classEntity);
        given(modelMapper.map(classEntity, ClassDTO.class)).willReturn(classDTO);

        ClassDTO result = classService.updateClass(classDTO);

        assertEquals(classDTO.getId(), result.getId());
        verify(classRepository).save(classEntity);
    }

    @Test
    @DisplayName("Test get all method")
    void testGetAll() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Class> classes = Arrays.asList(classEntity);
        Page<Class> classPage = new PageImpl<>(classes, pageable, classes.size());

        given(classRepository.findAll(pageable)).willReturn(classPage);
        given(modelMapper.map(classEntity, ClassDTO.class)).willReturn(classDTO);

        Page<ClassDTO> result = classService.getAllClass(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Java", result.getContent().get(0).getName());
    }

    @Test
    @DisplayName("Test search by name and roomNumber method")
    void testSearchByNameAndRoomNumber() {
        given(classRepository.findByNameAndRoomNumber("Java", 25)).willReturn(Optional.ofNullable(classEntity));
        given(modelMapper.map(classEntity, ClassDTO.class)).willReturn(classDTO);

        Optional<ClassDTO> result = classService.findClassByNameAndRoomNumber("Java", 25);

        assertEquals(classDTO.getId(), result.get().getId());
    }

}
