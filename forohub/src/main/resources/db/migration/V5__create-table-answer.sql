CREATE TABLE answers(

    id BIGINT NOT NULL AUTO_INCREMENT,
    answer VARCHAR(100) NOT NULL ,
    created_at DATETIME,
    updated_at DATETIME,
    user_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL ,

    PRIMARY KEY (id),
    CONSTRAINT fk_answers_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_answers_topic_id FOREIGN KEY (topic_id) REFERENCES topics(id)
);