package com.example.EduBridge.services.impl;

import com.example.EduBridge.dto.ClassDTO;
import com.example.EduBridge.exceptions.AlreadyExistsException;
import com.example.EduBridge.exceptions.DoesNotExistsException;
import com.example.EduBridge.models.Class;
import com.example.EduBridge.repositories.ClassRepository;
import com.example.EduBridge.services.ClassService;
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
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final ModelMapper modelMapper;

    @Override
    public ClassDTO saveClass(ClassDTO classDTO) {
        if (classRepository.existsById(classDTO.getId())) {
            throw new AlreadyExistsException("Class with id " + classDTO.getId() + " already exists");
        }
        Class classEntity = modelMapper.map(classDTO, Class.class);
        classEntity = classRepository.save(classEntity);
        return modelMapper.map(classEntity, ClassDTO.class);
    }

    @Override
    public Optional<ClassDTO> getClass(Long id) {
        if (classRepository.existsById(id)) {
            Class classEntity = classRepository.findById(id).get();
            return Optional.of(modelMapper.map(classEntity, ClassDTO.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public ClassDTO updateClass(ClassDTO classDTO) {
        if (classRepository.existsById(classDTO.getId())) {
            Class classEntity = modelMapper.map(classDTO, Class.class);
            classEntity = classRepository.save(classEntity);
            return modelMapper.map(classEntity, ClassDTO.class);
        } else {
            throw new DoesNotExistsException("Class with id " + classDTO.getId() + " does not exist");
        }
    }

    @Override
    public void deleteClass(Long id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
        }else {
            throw new DoesNotExistsException("Class with id " + id + " does not exist");
        }
    }

    @Override
    public List<ClassDTO> getAllClass() {
        List<Class> classes = classRepository.findAll();
        return classes.stream()
                .map(classEntity -> modelMapper.map(classEntity, ClassDTO.class))
                .collect(Collectors.toList());
    }
}
