package com.forohub.forohub.domain.course;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
public class Course
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title ;
    private String description;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Course(CourseNewDTO courseNewDTO){
        this.title= courseNewDTO.title();
        this.description= courseNewDTO.description();
        this.createdAt=LocalDateTime.now();
        this.status=true;
    }


    public void update(CourseUpdateDTO courseUpdateDTO) {

        if(courseUpdateDTO.title()!=null){
            this.title=courseUpdateDTO.title();
        }
        if(courseUpdateDTO.description()!=null){
            this.description=courseUpdateDTO.description();
        }
        if(courseUpdateDTO.status()){
            this.status=courseUpdateDTO.status();
        }
        this.updatedAt=LocalDateTime.now();

    }

    public void setStatus(Boolean status){
        this.status=status;
        this.updatedAt=LocalDateTime.now();
    }

}
