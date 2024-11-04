package com.example.EduBridge.controllers;

import com.example.EduBridge.dto.TrainerDTO;
    import com.example.EduBridge.services.TrainerService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody TrainerDTO trainerDTO) {
        TrainerDTO savedTrainer = trainerService.saveTrainer(trainerDTO);
        return ResponseEntity.ok(savedTrainer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long id) {
        Optional<TrainerDTO> trainerOptional = trainerService.getTrainer(id);
        return trainerOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(@PathVariable Long id, @RequestBody TrainerDTO trainerDTO) {
        trainerDTO.setId(id);
        try {
            TrainerDTO updatedTrainer = trainerService.updateTrainer(trainerDTO);
            return ResponseEntity.ok(updatedTrainer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        try {
            trainerService.deleteTrainer(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        List<TrainerDTO> trainers = trainerService.getAllTrainer();
        return ResponseEntity.ok(trainers);
    }
}
