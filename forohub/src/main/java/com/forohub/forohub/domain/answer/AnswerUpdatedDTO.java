package com.forohub.forohub.domain.answer;

import jakarta.validation.constraints.NotNull;



public record AnswerUpdatedDTO(
        @NotNull
        Long id,
        String comment,
        Boolean status

) {
}
