package com.forohub.forohub.domain.enroll;

import jakarta.validation.constraints.NotNull;

public record EnrollNewDTO(
        @NotNull
        Long user_id,
        @NotNull
        Long course_id
) {
}
