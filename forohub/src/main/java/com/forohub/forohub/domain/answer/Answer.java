package com.forohub.forohub.domain.answer;

import com.forohub.forohub.domain.topic.Topic;
import com.forohub.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="answers")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Getter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;


    public Answer(String comment, LocalDateTime createdAt, LocalDateTime updatedAt, User user, Topic topic) {
        this.comment = comment;
        this.status = true;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.topic = topic;
    }

    public void update(AnswerUpdatedDTO answerUpdatedDTO){


        if(answerUpdatedDTO.status()) {
            this.status = answerUpdatedDTO.status();
        }
        if(answerUpdatedDTO.comment()!=null){
            this.comment=answerUpdatedDTO.comment();
        }

        this.updatedAt=LocalDateTime.now();

    }

    public void setStatus(Boolean status){
        this.status=status;
        this.updatedAt=LocalDateTime.now();

    }
}
