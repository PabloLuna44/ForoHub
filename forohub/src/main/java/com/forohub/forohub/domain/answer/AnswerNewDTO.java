package com.forohub.forohub.domain.answer;

import com.forohub.forohub.domain.topic.Topic;
import com.forohub.forohub.domain.user.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record AnswerNewDTO (
         @NotBlank
         String comment,
         @NotNull
         Long  userId,
         @NotNull
         Long topicId
){


}
