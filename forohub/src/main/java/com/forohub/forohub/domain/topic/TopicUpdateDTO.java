package com.forohub.forohub.domain.topic;


import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(
        @NotNull
        Long id,
        String title,
        String message,
        Boolean status,
        Long courseId

) {


}
