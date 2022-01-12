# Vote for cafe application

###Task
Build a voting system for deciding where to have lunch.

    2 types of users: admin and regular users
    Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
    Menu changes each day (admins do the updates)
    Users can vote on which restaurant they want to have lunch at
    Only one vote counted per user
    If user votes again the same day:
        If it is before 11:00 we assume that he changed his mind.
        If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

### Команды Curl
#### Кафе
```curl -H 'Authorization: Basic c21pdGhAeWFuZGV4LnJ1OnBhc3N3b3JkMQ=' localhost:8080/api/cafes``` - список существующих кафе
```curl -v -H 'Authorization: Basic cGV0cm92QHlhbmRleC5ydTpwYXNzd29yZDI=' -H 'Content-type: application/json' -d '{"id":null, "name":"Тестовое кафе"}' localhost:8080/api/admin/cafes``` - добавить новое кафе

#### Блюда
```curl -v -H 'Authorization: Basic cGV0cm92QHlhbmRleC5ydTpwYXNzd29yZDI=' api/cafes/1/dishes``` - вывести список блюд для кафе
```curl -v -H 'Authorization: Basic cGV0cm92QHlhbmRleC5ydTpwYXNzd29yZDI=' -X DELETE localhost:8080/api/admin/cafes/1/dishes/1``` - удалить блюдо
#### Голосование