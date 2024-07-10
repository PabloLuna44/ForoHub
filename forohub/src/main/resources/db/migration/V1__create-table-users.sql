CREATE TABLE users(

    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL ,
    email VARCHAR(100) NOT NULL ,
    password VARCHAR(300) NOT NULL ,
    status TINYINT NOT NULL ,
    created_at DATETIME,
    updated_at DATETIME,

    PRIMARY KEY (id)
);