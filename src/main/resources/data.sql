INSERT INTO cafes (name)
VALUES ('Ресторан Достоевский'),
       ('Кафе Эстерхази');

INSERT INTO dishes (description, price, cafe_id)
VALUES ('Салат Цезарь', 500, 1),
       ('Лингвини с лососем', 600, 1),
       ('Салат Оливье', 300, 2),
       ('Лазанья', 400, 2);

INSERT INTO users(name, surname)
VALUES ('Джон', 'Смит'),
       ('Петр', 'Петров'),
       ('Ольга', 'Ларина'),
       ('Анна', 'Каренина');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2);

INSERT INTO votes(created, cafe_id, user_id)
VALUES (now(), 1, 1),
       (now(), 1, 2),
       (now(), 2, 3),
       (now(), 2, 4);






