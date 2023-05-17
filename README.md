# MongoLoggerAPI
A Rest API Logger using Spring Boot and MongoDB

## Intro

The MongoLoggerAPI is a RESTful API to receive and return logs. It has been thought as a microservice to centralize a logger among a several another applications. Therefore, in a microservices architecture, the MongoLoggerAPI can be used to access all logs from all services.

The MongoLoggerAPI is a personal project to train my Spring Boot knowledge to develop, quickly and concisely, a microservice.

It has been chosen the MongoDB as database to improve performance, knowing it'd be a logger api, as such as to gain knowledge with noSQL databases.

**Note:** It's returning a pageable result, then it can easily be consumed by a future frontend application to display it.

## Overview
![get generic logs result](https://github.com/Lucas-Mol/MongoLoggerAPI/assets/93149981/0604c50d-1248-4ec6-b732-d86ab165a84e) <br/>
**Description:** At this example, you can see that GET HTTP URL is sending 3 parameters: user, from and to. In response, you can see it's returning the found logs and a complete pageable info to be consumed by frontend application.

![image](https://github.com/Lucas-Mol/MongoLoggerAPI/assets/93149981/63bd798d-dc93-4b73-8d85-c69419d9249e) <br/>
**Description:** At this example, you can see a login POST HTTP URL. It's sending a username and password which can be customized as a environment variable of your application before deployment and return a JWT token to authorize all another resquests. <br/> <br/>
_Note: The user login system was maintened as a default user because in the thought scope and context of its usability, it'll be just used by same person/enterprise_

## How to run it?

It must be installed:

- Java JDK 17
- Maven
- MongoDB

Just do the following steps:

1. Write on application.yml on resources (<a href="/src/main/resources/application.yml">application.yml</a>):
    - URL (As default, MongoDB will use: mongodb://localhost:27017/MongoLogger)
    - username (your MongoDB username)
    - password (your MongoDB password)
    - username (the username to authenticate login)
    - password (the password to authenticate login)
    - secret (a key used to encrypt the JWT tokens)
2. Run with java the JavaMongoDbApplication.java (<a href="/src/main/java/com/mongologgerapi/JavaMongoDbApplication.java">JavaMongoDbApplication.java</a>) (You can use your favorite IDE to just run the project)
3. While it's running, you can use the Swagger Documentation on http://localhost:8080/swagger-ui/index.html and even use it to test. (Just send a /login with configured username and password, copy the JWT Token and click in green button "Authorize" at the top, it'll authorize all requests)


## Tech Stack

- Java JDK 17
- Spring Boot
- Spring Data
- Spring Validation
- Spring Security (using JWT authentication, authorization and access control)
- Spring Doc (Swagger)
- Lombok
- Auth0 JWT API
- MongoDB
 

