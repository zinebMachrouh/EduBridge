package com.example.EduBridge.services;

import com.example.EduBridge.dto.ClassDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    ClassDTO saveClass(ClassDTO classDTO);
    Optional<ClassDTO> getClass(Long id);
    ClassDTO updateClass(ClassDTO classDTO);
    void deleteClass(Long id);
    Page<ClassDTO> getAllClass(Pageable pageable);
    Optional<ClassDTO> findClassByNameAndRoomNumber(String name, Integer roomNumber);
}
