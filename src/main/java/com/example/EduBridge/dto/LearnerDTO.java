package com.example.EduBridge.dto;

import jakarta.validation.constraints.NotBlank;

public class LearnerDTO extends UserDTO{
    @NotBlank(message = "class is required")
    private ClassDTO classDTO;
}
