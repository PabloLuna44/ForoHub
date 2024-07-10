package com.forohub.forohub.domain.answer;

import com.forohub.forohub.domain.topic.TopicResponseDTO;
import com.forohub.forohub.domain.user.UserResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AnswerResponseDTO (

        Long id,
        String comment,
        UserResponseDTO user,
        TopicResponseDTO topic,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

){

    public AnswerResponseDTO(Answer answer){
        this(
            answer.getId(),
            answer.getComment(),
            new UserResponseDTO(answer.getUser()),
            new TopicResponseDTO(answer.getTopic()),
            answer.getCreatedAt(),
            answer.getUpdatedAt()
        );

    }
}
