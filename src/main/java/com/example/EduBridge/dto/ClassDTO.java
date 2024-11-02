package com.example.EduBridge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassDTO {
    @Builder.Default
    private Long id = 0L;

    @NotBlank(message = "name is required")
    @Size(min = 1, max = 50, message = "name must be between 1 and 50 characters")
    private String name;

    @NotBlank(message = "roomNumber is required")
    @Min(value = 1, message = "roomNumber must be greater than 0")
    private Integer roomNumber;

    @NotBlank(message = "training is required")
    private TrainingDTO training;

    @NotBlank(message = "trainer is required")
    private TrainerDTO trainer;
}
