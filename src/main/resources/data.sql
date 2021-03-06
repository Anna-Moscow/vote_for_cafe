INSERT INTO cafes (name)
VALUES ('Ресторан Достоевский'),
       ('Кафе Эстерхази');

INSERT INTO dishes (description, price, cafe_id)
VALUES ('Салат Цезарь', 500, 1),
       ('Лингвини с лососем', 600, 1),
       ('Салат Оливье', 300, 2),
       ('Лазанья', 400, 2);

INSERT INTO users(name, surname, email, password)
VALUES ('Джон', 'Смит', 'smith@yandex.ru', '{noop}password1'),
       ('Петр', 'Петров', 'petrov@yandex.ru', '{noop}password2'),
       ('Ольга', 'Ларина', 'larina@yandex.ru', '{noop}password3'),
       ('Анна', 'Каренина', 'karenina@yandex.ru', '{noop}password4');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 3),
       ('USER', 4);

INSERT INTO votes(created, cafe_id, user_id)
VALUES (DATE_TRUNC('DAY', now()) + INTERVAL '10' hour, 1, 1),
       (DATE_TRUNC('DAY', now()) + INTERVAL '10' hour, 1, 2),
       (DATE_TRUNC('DAY', now()) + INTERVAL '12' hour, 2, 3),
       (DATE_TRUNC('DAY', now()) + INTERVAL '1' hour, 2, 4),
       (DATE_TRUNC('DAY', now()) + INTERVAL '2' hour, 1, 4);










