package com.forohub.forohub.domain.enroll;

import com.forohub.forohub.domain.course.CourseResponseDTO;
import com.forohub.forohub.domain.user.UserResponseDTO;

public record EnrollResponseDTO(
        Long id,
        UserResponseDTO user,
        CourseResponseDTO course
)
{
    public EnrollResponseDTO(Enroll enroll){
        this(
          enroll.getId(),
          new UserResponseDTO(enroll.getUser()),
          new CourseResponseDTO(enroll.getCourse())
        );
    }
}
