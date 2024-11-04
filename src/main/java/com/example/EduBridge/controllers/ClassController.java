package com.example.EduBridge.controllers;

import com.example.EduBridge.dto.ClassDTO;
import com.example.EduBridge.services.ClassService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/class")
public class ClassController {
    private final ClassService classService;

    @PostMapping
    public ResponseEntity<ClassDTO> createClass(@RequestBody ClassDTO classDTO) {
        ClassDTO savedClass = classService.saveClass(classDTO);
        return ResponseEntity.ok(savedClass);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Long id) {
        Optional<ClassDTO> classOptional = classService.getClass(id);
        return classOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassDTO> updateClass(@PathVariable Long id, @RequestBody ClassDTO classDTO) {
        classDTO.setId(id);
        try {
            ClassDTO updatedClass = classService.updateClass(classDTO);
            return ResponseEntity.ok(updatedClass);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        try {
            classService.deleteClass(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ClassDTO>> getAllClasses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClassDTO> classes = classService.getAllClass(pageable);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/search")
    public ResponseEntity<ClassDTO> findClassByNameAndRoomNumber(
            @RequestParam String name,
            @RequestParam Integer roomNumber) {
        Optional<ClassDTO> classOptional = classService.findClassByNameAndRoomNumber(name, roomNumber);
        return classOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
