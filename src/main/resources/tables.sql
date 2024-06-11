CREATE TABLE IF NOT EXISTS Comment (
    id BIGSERIAL NOT NULL,
    text VARCHAR(100) NOT NULL,
    date DATE not null,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Users (
    id BIGSERIAL NOT NULL ,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (id, email)
);

CREATE TABLE IF NOT EXISTS Task (
    id BIGSERIAL NOT NULL,
    head VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE Comment ADD COLUMN task_id BIGINT;
ALTER TABLE Comment ADD FOREIGN KEY (task_id)REFERENCES Task(id);


ALTER TABLE Task ADD COLUMN author BIGINT;
ALTER TABLE Task ADD COLUMN executor BIGINT;
ALTER TABLE Task ADD FOREIGN KEY (author) REFERENCES Users (id);
ALTER TABLE Task ADD FOREIGN KEY (executor) REFERENCES Users (id);

INSERT INTO Users(username, email, password, role)
VALUES ('Dima', 'dima@mail.ru', '$2a$12$mNIVEvxMayayx8h6qT5O7ugAzs/1p00iTkgnfdMJPgoRzD48aqAne', 'ADMIN'), ('Liza', 'liza@mail.ru', '$2a$12$p1aAORLo31Qh853XX.N4EeRKM2n6PCkQTSdll8Ts.plY4QNEILNru', 'USER'),
       ('Mira', 'mira@mail.ru','$2a$12$p1aAORLo31Qh853XX.N4EeRKM2n6PCkQTSdll8Ts.plY4QNEILNru', 'USER');

INSERT INTO Task (head, description, status, priority, author, executor)
VALUES ('Магазин', 'Сходить в магазин', 'IN_PROGRESS', 'MIDDLE', 2, 1),
       ('Уборка', 'Убрать на кухне', 'IN_PROGRESS', 'LOW', 1, 3);

INSERT INTO  Comment(text, date, task_id)
VALUES ('почему я?', CURRENT_DATE, 1),
       ('могу только разбросаться на кухне', CURRENT_DATE, 2)