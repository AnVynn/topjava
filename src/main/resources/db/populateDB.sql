DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '22.01.2021 08:00', 'Завтрак', 550),
       (100000, '22.01.2021 15:15', 'Обед', 1050),
       (100000, '22.01.2021 20:00', 'Ужин', 650),
       (100001, '12.01.2021 9:30', 'Завтрак', 500),
       (100001, '12.01.2021 14:30', 'Обед', 1150),
       (100001, '12.01.2021 19:45', 'Ужин', 200);
