package com.forohub.forohub.domain.enroll;

import jakarta.validation.constraints.NotNull;

public record EnrollNewDTO(
        @NotNull
        Long userId,
        @NotNull
        Long courseId
) {
}
