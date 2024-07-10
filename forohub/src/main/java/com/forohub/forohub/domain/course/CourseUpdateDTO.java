package com.forohub.forohub.domain.course;


import jakarta.validation.constraints.NotNull;



public record CourseUpdateDTO(
        @NotNull
        Long id,
        String title ,
        String description,
        Boolean status

) {
}
