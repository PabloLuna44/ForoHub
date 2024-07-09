package com.forohub.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CourseUpdateDTO(
        @NotNull
        Long id,
        String title ,
        String description,
        Boolean status

) {
}
