package com.forohub.forohub.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AnswerResponseDTO (

        String comment,
        Boolean status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

){

    public AnswerResponseDTO(Answer answer){
        this(
            answer.getComment(),
            answer.getStatus(),
            answer.getCreatedAt(),
            answer.getUpdatedAt()
        );

    }
}
