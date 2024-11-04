package com.example.EduBridge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class TrainerDTO extends UserDTO{
    @NotBlank(message = "speciality is required")
    @Size(min = 3, max = 50, message = "speciality must be between 3 and 50 characters")
    private String speciality;
}
