package com.forohub.forohub.domain.topic;

import com.forohub.forohub.domain.course.CourseResponseDTO;
import com.forohub.forohub.domain.user.UserResponseDTO;

public record TopicResponseDTO(

        String title,
        String message,
        UserResponseDTO user,
        CourseResponseDTO course

) {
    public TopicResponseDTO(Topic topic){
        this(
            topic.getTitle(),
            topic.getMessage(),
            new UserResponseDTO(topic.getUser()),
            new CourseResponseDTO(topic.getCourse())
        );
    }
}
