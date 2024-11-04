package com.example.EduBridge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class LearnerDTO extends UserDTO{
    @NotBlank(message = "class is required")
    private ClassDTO classDTO;
}
