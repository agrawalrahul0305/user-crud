# User-CRUD-Operations

This codebase is created to demonstrate Spring Boot + JWT + JPA including authentcation and authorization CRUD operations for a user.

# Security

Integration with Spring Security and add other filter for jwt token process.

# Database

It uses MySql database, application.properties is configured for connection details.

# Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.assessment.UserCrudAssessmentApplication class from your IDE.

Download the zip or clone the Git repository.
Unzip the zip file (if you downloaded one)
Open Command Prompt and Change directory (cd) to folder containing pom.xml
Open Eclipse
File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
Select the project
Choose the Spring Boot Application file (search for @SpringBootApplication)
Right Click on the file and Run as Java Application

# Packages

models — to hold our entities;

repositories — to communicate with the database;

services — to hold our business logic;

controllers — to listen to the client;

resources/ - Contains all the static resources, templates and property files.

resources/application.properties - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.

test/ - contains unit and integration tests

pom.xml - contains all the project dependencies
