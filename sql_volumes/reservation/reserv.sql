CREATE DATABASE reservDB;

USE reservDB;

CREATE TABLE reservation
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    arrive date                  NULL,
    depart date                  NULL,
    ref    char(36)              NOT NULL UNIQUE,
    status VARCHAR(255)          NULL,
    CONSTRAINT pk_reservation PRIMARY KEY (id)
);