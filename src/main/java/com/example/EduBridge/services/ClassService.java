package com.example.EduBridge.services;

import com.example.EduBridge.dto.ClassDTO;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    ClassDTO saveClass(ClassDTO classDTO);
    Optional<ClassDTO> getClass(Long id);
    ClassDTO updateClass(ClassDTO classDTO);
    void deleteClass(Long id);
    List<ClassDTO> getAllClass();
}
