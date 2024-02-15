# Task Management System

This is the Education Project, a Java Technical Assessment for Digitinary.

This project is aimed at creating RESTFul services for a Task Management System. It involves registering users,
creating tasks, and various other activities related to tasks interactions.

## Technologies Used

- Spring Boot 3.2.2
- Java 17
- MySQL Database
- JWT Authentication
- Lombok for code generation
- SpringDoc for API documentation (Swagger)

## IDE Used

You can use any Java IDE of your choice to work with this project. Some popular options include: (I use IntelliJ IDEA
Community Edition):

- IntelliJ IDEA
- Eclipse
- Visual Studio Code

## Dependencies

- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Web
- MySQL Connector
- Spring Boot DevTools
- Spring Boot Starter Test
- Spring Security Test
- jjwt library for JWT
- Lombok for code generation
- SpringDoc for API documentation (Swagger)

Please refer to the `pom.xml` for the complete list of dependencies.

## Building and Running

- To build and run the project:
    1. Configure your MySQL database settings in `application.properties`.
    2. Import the project into your chosen IDE.
    3. Run the main application class to start the Spring Boot application.
    4. Access the application's API documentation using the provided URL (
       usually http://localhost:8080/swagger-ui.html).

- To Build and run the project using docker:
    1. Go to project directory
    2. run this command `mvn clean package && docker build .`

## Important URLs

APIs documentation:

1. Swagger (http://localhost:8080/swagger-ui/index.html).
2. Postman (https://documenter.getpostman.com/view/13790770/2sA2r6Y4nD).

## Important Files

DataBase Files:

1. dump-structure.sql (For create empty database).
2. Task Management.postman_collection.json (For Test the APIs in Postman).