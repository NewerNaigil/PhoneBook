# PhoneBook

Программа представляет собой серверную часть приложения по работе с пользователями и их телефонной книжкой.

Программа предостовляет REST API для:

***

####*Создание пользователя:

POST http://localhost:8080/users

Параметры запроса:

    firstName (String) - Имя пользователя *Обязательное поле
    
    lastName (String) - Фамилия пользователя
    
    phoneBook (ArrayList) - Телефонная книга
    
    recordName (String) - Имя записи в телефонной книге
    
    recordNumber (String) - Телефонный номер записи в телефонной книге

Пример запррса:

Body: JSON

    {
        "firstName" : "Vova",
        "lastName" : "Makarov",
        "phoneBook": [
            {"recordName": "Petya", "recordNumber": "111111"},
            {"recordName": "Misha", "recordNumber": "222222"}
        ]
    }
В случе умпеха вернется статус 201 Created.

Если не указать Имя пользователя вернётся статус 400 Bad Request.

***

####*Получение пользователя по ID:

GET http://localhost:8080/users/{userID}

Параметры запроса:

    userID (long) - ID пользователя которого необходимо получить
Пример запррса:

    http://localhost:8080/users/1


В случе уcпеха вернется статус 200 ok

Пример ответа:

    {
        "userID": 1,
        "firstName": "Vova",
        "lastName": "Makarov",
        "phoneBook": [
            {
                "recordId": 1,
                "recordName": "Petya",
                "recordNumber": "111111"
            },
            {
                "recordId": 2,
                "recordName": "Misha",
                "recordNumber": "222222"
            }
        ]
    }
В случае указания не верного ID вернётся статус 404 Not Found

***

####*Удаление пользователя

DELETE http://localhost:8080/users

Параметры запроса:

    userID (long) - ID пользователя которого необходимо удалить
Пример запррса:

Body: form-data

    key:        value:
    userID      1

В случе уcпеха вернется статус 200 ok

В случае указания не верного ID вернётся статус 404 Not Found

***

####*Редоктирование пользователя

PATCH http://localhost:8080/users