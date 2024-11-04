package com.example.EduBridge.dto;

import com.example.EduBridge.models.enums.TrainingStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrainingDTO {
    @Builder.Default
    private Long id = 0L;

    @NotBlank(message = "title is required")
    @Size(min = 3, max = 50, message = "title must be between 3 and 50 characters")
    private String title;

    @NotBlank(message = "level is required")
    @Size(min = 3, message = "level must be between 3 and 50 characters")
    private String level;

    @NotBlank(message = "prerequisites is required")
    @Size(min = 3, message = "prerequisites must be between 3 and 50 characters")
    private String prerequisites;

    @NotBlank(message = "minCapacity is required")
    @Min(value = 1, message = "minCapacity must be greater than 0")
    private Integer minCapacity;

    @NotBlank(message = "maxCapacity is required")
    @Min(value = 1, message = "maxCapacity must be greater than 0")
    private Integer maxCapacity;

    @NotBlank(message = "startDate is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotBlank(message = "endDate is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotBlank(message = "status is required")
    private TrainingStatus status;
}
