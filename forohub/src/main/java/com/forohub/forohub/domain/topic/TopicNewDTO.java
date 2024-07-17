package com.forohub.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicNewDTO(
        @NotBlank
          String title,
          @NotBlank
          String message,
          @NotNull
          Long userId,
          @NotNull
          Long courseId) {
}
