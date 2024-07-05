CREATE TABLE topics(

    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL UNIQUE ,
    message VARCHAR(100) NOT NULL UNIQUE ,
    created_at DATETIME,
    updated_at DATETIME,
    status TINYINT,
    user_id BIGINT NOT NULL ,
    course_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_topic_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_topic_course_id FOREIGN KEY (course_id) REFERENCES courses(id)

);