--liquibase formatted sql

--changeset grogu:1
INSERT INTO Users(username, email, password, role)
VALUES ('Dima', 'dima@mail.ru', '$2a$12$mNIVEvxMayayx8h6qT5O7ugAzs/1p00iTkgnfdMJPgoRzD48aqAne', 'ADMIN'), ('Liza', 'liza@mail.ru', '$2a$12$p1aAORLo31Qh853XX.N4EeRKM2n6PCkQTSdll8Ts.plY4QNEILNru', 'USER'),
       ('Mira', 'mira@mail.ru','$2a$12$p1aAORLo31Qh853XX.N4EeRKM2n6PCkQTSdll8Ts.plY4QNEILNru', 'USER');

--changeset grogu:2
INSERT INTO Task (head, description, status, priority, author, executor)
VALUES ('Магазин', 'Сходить в магазин', 'IN_PROGRESS', 'MIDDLE', 2, 1),
       ('Уборка', 'Убрать на кухне', 'IN_PROGRESS', 'LOW', 1, 3);

--changeset grogu:3
INSERT INTO  Comment(text, date, task_id)
VALUES ('почему я?', CURRENT_DATE, 1),
       ('могу только разбросаться на кухне', CURRENT_DATE, 2)