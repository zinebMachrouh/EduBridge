package com.example.EduBridge.controllers;

import com.example.EduBridge.dto.TrainingDTO;
import com.example.EduBridge.services.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/training")
public class TrainingController {
    private final TrainingService trainingService;

    @PostMapping
    public ResponseEntity<TrainingDTO> createTraining(@RequestBody TrainingDTO trainingDTO) {
        TrainingDTO savedTraining = trainingService.saveTraining(trainingDTO);
        return ResponseEntity.status(201).body(savedTraining); // HTTP 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDTO> getTraining(@PathVariable Long id) {
        Optional<TrainingDTO> trainingDTO = trainingService.getTraining(id);
        return trainingDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // HTTP 404 Not Found if not present
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDTO> updateTraining(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO) {
        if (!id.equals(trainingDTO.getId())) {
            return ResponseEntity.badRequest().build(); // HTTP 400 Bad Request if ID in path and body don't match
        }
        TrainingDTO updatedTraining = trainingService.updateTraining(trainingDTO);
        return ResponseEntity.ok(updatedTraining); // HTTP 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    @GetMapping
    public ResponseEntity<Page<TrainingDTO>> getAllTrainings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TrainingDTO> trainingPage = trainingService.getAllTraining(pageable);
        return ResponseEntity.ok(trainingPage);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TrainingDTO>> searchTrainings(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TrainingDTO> trainingPage = trainingService.searchTrainingByTitle(title, pageable);
        return ResponseEntity.ok(trainingPage);
    }
}
