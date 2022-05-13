CREATE DATABASE factureDB;

USE factureDB;

CREATE TABLE facture
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    prix       DOUBLE                NULL,
    reservRef char(36)              NOT NULL,
    CONSTRAINT pk_facture PRIMARY KEY (id)
) engine = InnoDB;

ALTER TABLE facture
    ADD CONSTRAINT uc_facture_reservref UNIQUE (reservRef);