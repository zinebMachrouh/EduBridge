package com.example.EduBridge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class UserDTO {
    @Builder.Default
    private Long id = 0L;

    @NotBlank(message = "fname is required")
    @Size(min = 3, max = 25, message = "fname must be between 3 and 25 characters")
    private String fname;

    @NotBlank(message = "lname is required")
    @Size(min = 3, max = 25, message = "lname must be between 3 and 25 characters")
    private String lname;

    @NotBlank(message = "email is required")
    @Email(message = "email must be a valid email address")
    private String email;
}
