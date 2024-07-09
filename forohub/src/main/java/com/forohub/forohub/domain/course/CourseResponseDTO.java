package com.forohub.forohub.domain.course;

import java.time.LocalDateTime;

public record CourseResponseDTO(
         Long id,
         String title ,
         String description,
         LocalDateTime createdAt,
         LocalDateTime updatedAt
) {
    public CourseResponseDTO(Course course){
        this(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }
}
