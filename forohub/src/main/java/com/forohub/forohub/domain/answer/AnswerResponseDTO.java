package com.forohub.forohub.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AnswerResponseDTO (

        Long id,
        String comment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

){

    public AnswerResponseDTO(Answer answer){
        this(
            answer.getId(),
            answer.getComment(),
            answer.getCreatedAt(),
            answer.getUpdatedAt()
        );

    }
}
