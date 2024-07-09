package com.forohub.forohub.domain.topic;

import com.forohub.forohub.domain.course.CourseResponseDTO;
import com.forohub.forohub.domain.user.UserResponseDTO;

import java.time.LocalDateTime;

public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UserResponseDTO user,
        CourseResponseDTO course

) {
    public TopicResponseDTO(Topic topic){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreatedAt(),
                topic.getUpdatedAt(),
                new UserResponseDTO(topic.getUser()),
                new CourseResponseDTO(topic.getCourse())
        );
    }
}