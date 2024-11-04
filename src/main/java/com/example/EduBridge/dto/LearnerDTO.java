package com.example.EduBridge.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class LearnerDTO extends UserDTO{
    @NotNull(message = "classDTO is required")
    private ClassDTO classEntity;
}
