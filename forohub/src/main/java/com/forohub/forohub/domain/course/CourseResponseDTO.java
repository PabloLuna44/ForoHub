package com.forohub.forohub.domain.course;

public record CourseResponseDTO(
         Long id,
         String name ,
         String description
) {
    public CourseResponseDTO(Course course){
        this(
                course.getId(),
                course.getName(),
                course.getDescription()
        );
    }
}
