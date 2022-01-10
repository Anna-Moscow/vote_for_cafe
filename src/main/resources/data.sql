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
VALUES (current_date + INTERVAL '10' hour, 1, 1), /*голос за 1 */
       (current_date + INTERVAL '10' hour, 1, 2), /*админ. нет голоса*/
       (current_date + INTERVAL '12' hour, 2, 3), /* поздно. нет голоса  !не работает, этот голос появляяется*/
       (current_date + INTERVAL '1' hour, 2, 4), /*пользователь передумал !не работает, появляется этот вместо следующего*/
       (current_date + INTERVAL '2' hour, 1, 4); /* голос за 1 * !не работает, см выше*/










