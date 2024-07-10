package com.forohub.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;

public record CourseNewDTO(
         @NotBlank
         String title,
         @NotBlank
         String description

) {
}
