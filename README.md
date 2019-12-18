# PhoneBook

Программа представляет собой серверную часть приложения по работе с пользователями и их телефонной книжкой.

***

Запуск программы осуществляется из класса PhoneBookApplication.

***

Программа предостовляет REST API для:

***

#### *Создание пользователя:

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
В случае успеха вернётся статус 201 Created.

Если не указать Имя пользователя вернётся статус 400 Bad Request.

***

#### *Получение пользователя по ID:

GET http://localhost:8080/users/{userId}

Параметры запроса:

    userID (long) - ID пользователя которого необходимо получить
    
Пример запррса:

    http://localhost:8080/users/1

В случае указания не верного ID вернётся статус 404 Not Found

В случае успеха вернётся статус 200 ok.

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

***

#### *Удаление пользователя

DELETE http://localhost:8080/users

Параметры запроса:

    userID (long) - ID пользователя которого необходимо удалить
    
Пример запррса:

Body: form-data

    key:        value:
    userID      1

В случае успеха вернётся статус 200 ok

В случае указания не верного ID вернётся статус 404 Not Found

***

#### *Редактирование пользователя

PATCH http://localhost:8080/users

Параметры запроса:

    userID (long) - ID пользователя которого необходимо удалить
    
    firstName (String) - Имя пользователя
        
    lastName (String) -  Фамилия пользователя
    
Пример запррса:

Body: form-data

    key:        value:
    userID      1
    firstName   Vika
    lastName    Simon

В случае успеха вернётся статус 200 ok

В случае указания не верного ID вернётся статус 404 Not Found

***

#### *Получения списка всех пользователей

GET http://localhost:8080/users

В случае успеха вернётся статус 200 ok

Если не одного пользователя не создано, то вернётся пустое тело и статус 200 ok

Пример ответа:

    {
        "1": {
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
        },
        "2": {
            "userID": 2,
            "firstName": "Lena",
            "lastName": "Ivanola",
            "phoneBook": [
                {
                    "recordId": 3,
                    "recordName": "Kiril",
                    "recordNumber": "333333"
                },
                {
                    "recordId": 4,
                    "recordName": "Olya",
                    "recordNumber": "444444"
                }
            ]
        }
    }
    
***

#### *Поиск пользователей по имени (или его части)

GET http://localhost:8080/users/search/{searchRequest}

Параметры запроса:

    searchRequest (String) - запрос на поиск пользователя

Пример запррса:

    http://localhost:8080/users/search/vo

В случае успеха вернётся статус 200 ok, и в теле ответа результат поиска.

Если в результате запроса ничего не найдено, то вернётся пустое тело и статус 200 ok.

Пример ответа:

    [
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
        },
        {
            "userID": 2,
            "firstName": "Volfgan",
            "lastName": "Kurt",
            "phoneBook": [
                {
                    "recordId": 3,
                    "recordName": "Kiril",
                    "recordNumber": "111111"
                },
                {
                    "recordId": 4,
                    "recordName": "Olya",
                    "recordNumber": "222222"
                }
            ]
        }
    ]

***

#### *Создание записи в телефонной книжке пользователя

POST http://localhost:8080/records

Параметры запроса:

    userID (long) - ID пользователя которому принадлежит телефонная книга
    
    recordName (String) - Имя записи в телефонной книге
    
    recordNumber (String) - Телефонный номер записи в телефонной книге

Пример запррса:

Body: form-data

    key:            value:
    userID          1
    recordName      Sveta
    recordNumber    999999

В случае успеха вернётся статус 200 ok

В случае указания не верного ID вернётся статус 404 Not Found

***

#### *Получение телефонной записи по ID:

GET http://localhost:8080/recorsd/{userId}/{recordId}

Параметры запроса:

    userID (long) - ID пользователя которому принадлежит телефонная книга
    
    recordId (long) - ID записи в телефонной книге пользователя
Пример запррса:

    http://localhost:8080/records/1/2

В случае указания не верного ID вернётся статус 404 Not Found

В случае успеха вернётся статус 200 ok

Пример ответа:

    {
        "recordId": 2,
        "recordName": "Misha",
        "recordNumber": "222222"
    }
    
***

#### *Удаление телефонной записи

DELETE http://localhost:8080/recods

Параметры запроса:

    userID (long) - ID пользователя которому принадлежит телефонная книга
    
    recordId (long) - ID записи в телефонной книге пользователя
    
Пример запррса:

Body: form-data

    key:        value:
    userID      1
    recordId    2

В случае успеха вернётся статус 200 ok

В случае указания не верного ID вернётся статус 404 Not Found

***

#### *Редактирование телефонной записи

PATCH http://localhost:8080/records

Параметры запроса:

    userID (long) - ID пользователя которому принадлежит телефонная книга
    
    recordId (long) - ID записи в телефонной книге пользователя
    
    recordName (String) - Имя новой записи в телефонной книге
        
    recordNumber (String) - Телефонный номер новой записи в телефонной книге
        
Пример запррса:

Body: form-data

    key:            value:
    userID          1
    recordId        2
    recordName      Mark
    recordNumber    999999

В случае успеха вернётся статус 200 ok

В случае указания не верного ID вернётся статус 404 Not Found

***

#### *Получения списка всех записей в телефонной книжке пользователя

GET http://localhost:8080/records/{userId}

Параметры запроса:

    userID (long) - ID пользователя которому принадлежит телефонная книга

Пример запроса:

    http://localhost:8080/records/1

В случае успеха вернётся статус 200 ok

В случае указания не верного ID вернётся статус 404 Not Found

Пример ответа:

    [
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
    
***

### *Поиск телефонной записи по номеру телефона

GET http://localhost:8080/records/search/{userId}/{searchRequest}

Параметры запроса:

    userID (long) - ID пользователя которому принадлежит телефонная книга
    
    searchRequest (String) - запрос на поиск телефонной записи
    
Пример запроса:

    http://localhost:8080/records/search/1/222222
    
В случае указания не верного ID пользователя вернётся статус 404 Not Found

В случае указания не верного номера телефона вернётся статус 404 Not Found

В случае успеха вернётся статус 200 ok, и в теле ответа результат поиска.

Пример ответа:

    [
        {
            "recordId": 2,
            "recordName": "Misha",
            "recordNumber": "222222"
        }
    ]