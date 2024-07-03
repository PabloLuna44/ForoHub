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
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;


    public Topic(String title,String message,LocalDateTime creationDate,Status status,User user,Course course){

        this.title=title;
        this.message=message;
        this.creationDate=creationDate;
        this.status=status;
        this.user=user;
        this.course=course;

    }
}
