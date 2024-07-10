package com.forohub.forohub.domain.topic;

import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="topics")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Getter
public class Topic
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;


    public Topic(String title,String message,LocalDateTime creationDate,User user,Course course){

        this.title=title;
        this.message=message;
        this.createdAt=creationDate;
        this.status=true;
        this.user=user;
        this.course=course;

    }

    public void update(String title,String message,Boolean status,Course course){

        if(title!=null){
            this.title=title;
        }
        if(message!=null){
            this.message=message;
        }
        if (status != null){
            this.status=status;
        }
        if(course!=null){
            this.course=course;
        }
        this.updatedAt=LocalDateTime.now();

    }

    public void setStatus(Boolean status){
        this.status=status;
        this.updatedAt=LocalDateTime.now();
    }
}
