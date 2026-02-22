# Spring Boot Engineer Managment API

I built this RESTful API with Spring boot that provides CRUD operations to manage a database of engineers.
This project demonstrates Springboot fundamentals, Exception handling, JPA and dockerized PostgreSQL. 


## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Maven


## API Endpoints

| Method | Endpoint                   | Description           |
|--------|--------------------------- |-----------------------|
| GET    | /api/v1/engineers          | Get all engineers     |
| GET    | /api/v1/engineers/{id}     | Get engineer by id    |
| POST   | /api/v1/engineers          | Create new engineer   |
| PUT    | /api/v1/engineers/{id}     | Update engineer by id |
| DELETE | /api/v1/engineers/{id}     | Delete engineer by id |
