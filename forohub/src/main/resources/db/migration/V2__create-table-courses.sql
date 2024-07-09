
CREATE TABLE courses(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL ,
    description VARCHAR(100) NOT NULL ,
    status TINYINT,
    createdAt DATETIME,
    updatedAt DATETIME,

    PRIMARY KEY (id)
);