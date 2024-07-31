--liquibase formatted sql

--changeset grogu:1
CREATE TABLE IF NOT EXISTS Users (
     id BIGSERIAL NOT NULL ,
     username VARCHAR(50) NOT NULL,
     email VARCHAR(50) NOT NULL,
     password VARCHAR(255) NOT NULL,
     role VARCHAR(50) NOT NULL,
     PRIMARY KEY (id)
);
--changeset grogu:2
CREATE TABLE IF NOT EXISTS Task (
    id BIGSERIAL NOT NULL,
    head VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

--changeset grogu:3
CREATE TABLE IF NOT EXISTS Comment (
   id BIGSERIAL NOT NULL,
   text VARCHAR(100) NOT NULL,
   date DATE not null,
   PRIMARY KEY (id)
);

--changeset grogu:4
ALTER TABLE Comment ADD COLUMN task_id BIGINT;
ALTER TABLE Comment ADD FOREIGN KEY (task_id)REFERENCES Task(id);
ALTER TABLE Task ADD COLUMN author BIGINT;
ALTER TABLE Task ADD COLUMN executor BIGINT;
ALTER TABLE Task ADD FOREIGN KEY (author) REFERENCES Users (id);
ALTER TABLE Task ADD FOREIGN KEY (executor) REFERENCES Users (id);