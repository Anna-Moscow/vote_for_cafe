INSERT INTO users (name, surname)
VALUES ('User', 'user@yandex.ru'),
       ('Admin', 'admin@gmail.com');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2);

// добавить анотации к dishes в кафе для создания таблицы

INSERT INTO dishes (description, price, cafe_id)
VALUES ('Салат Цезарь', 400, 1),
       ('Лингвини с креветками', 500, 1),
       ('Салат Оливье', 300, 2),
       ('Лазанья', 400, 2);

INSERT INTO cafes (name, rating)
VALUES ('Ресторан Достоевский', 4.5),
       ('Кафе Эстерхази', 4.6);


