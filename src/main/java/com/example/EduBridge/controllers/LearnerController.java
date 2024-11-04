package com.example.EduBridge.controllers;

import com.example.EduBridge.dto.LearnerDTO;

import com.example.EduBridge.services.LearnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/learner")
public class LearnerController {
    private final LearnerService learnerService;

    @PostMapping
    public ResponseEntity<LearnerDTO> createLearner(@RequestBody LearnerDTO learnerDTO) {
        LearnerDTO savedLearner = learnerService.saveLearner(learnerDTO);
        return ResponseEntity.status(201).body(savedLearner); // HTTP 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearnerDTO> getLearner(@PathVariable Long id) {
        Optional<LearnerDTO> learnerDTO = learnerService.getLearner(id);
        return learnerDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearnerDTO> updateLearner(@PathVariable Long id, @RequestBody LearnerDTO learnerDTO) {
        if (!id.equals(learnerDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        LearnerDTO updatedLearner = learnerService.updateLearner(learnerDTO);
        return ResponseEntity.ok(updatedLearner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearner(@PathVariable Long id) {
        learnerService.deleteLearner(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<LearnerDTO>> getAllLearners() {
        List<LearnerDTO> trainers = learnerService.getAllLearner();
        return ResponseEntity.ok(trainers);
    }
}
