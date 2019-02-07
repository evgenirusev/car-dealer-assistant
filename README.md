# Car Dealer Assistant

## Description
Car Dealer Assistant is an open source Spring Framework based web application for managing car dealership business logictics and infrastructure data. Sign in as User to analyze the data, or sign in as Administrator to execute modifications and actions on the data.

## Database Schema:
![Sorry, error loading image of diagram](http://evgeni-rusev.com/dealer-db.png)

## Technologies
- Java:
  - Spring Framework:
    - Spring MVC:
      - application-level on the basis on design pattern: model-view-controller
      - using methods "get" and "post" with parameterising URLs which is typical of REST API
    - Spring Data:
      - using JPQL and ready-made methods from `JpaRepository` to creating, reading, updating and deleting data
      - implementation of native queries
    - Spring Security:
      - own login form with authentication of users on the basis of database
      - restricting access to some pages for offline users
      - protection against cross-site request forgery
      - encoding passwords
    - Spring Boot:
      - automatic configuration and launching application 
  - JPA & Hibernate:
    - specifying relations between entities in database and parameters of columns in tables
  - Java 10 SE:
    - Optionals, LocalDateTime
- HTML:
  - Thymeleaf
  - data validation in login form and registration form
  - semantic elements from HTML5
