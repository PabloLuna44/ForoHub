CREATE TABLE user_course(

    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL ,
    course_id BIGINT NOT NULL ,
    cratedAt DATETIME,

    PRIMARY KEY (id),
    CONSTRAINT fk_user_course_user_id FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_user_course_course_id FOREIGN KEY(course_id) REFERENCES courses(id)


);