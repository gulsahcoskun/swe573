[![CircleCI](https://circleci.com/gh/gulsahcoskun/swe573/tree/master.svg?style=svg)](https://circleci.com/gh/gulsahcoskun/swe573/tree/master)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=gulsahcoskun_swe573&metric=alert_status)](https://sonarcloud.io/dashboard?id=gulsahcoskun_swe573)

# swe573  "G-LEARN" 
>Term project of SWE573 course of Bogazici University about social media exploration platform.  
The project aims to be a learning space for learners and teachers.

Technologies used in G-Learn:
1. Java 1.8
2. Spring Boot (Embedded Tomcat Server)
3. Angular
4. Wikidata
5. Postgresql
6. CircleCI (Continuous Integration)
7. Heroku (Cloud Platform as a Service)
8. SonarCloud (Code Quality)

--------------------------------

## Install

In order to work with the backend project -> swe573
> Java 1.8, Postgresql and  Maven should be installed.  

In order to work with the frontend project -> swe573-ui 
> Node.js, npm package manager and angular/cli should be intalled.  

Both swe573 and swe573-ui should be cloned to local repositories.  

CircleCI and  SonarCloud can be followed by the links above by clicking the badges. 

Since it is a Spring Boot application, backend can run on embedded Tomcat server locally.  

It is currently deployed in Heroku in production environment.

-------------------------------------------

## Configure

Project is made to work with Postgresql database. 

The configurations can be found in application.properties of the application. Information like database name, username, password should be entered based on local database configuration.  

Also, a different database can be configured, but related jdbc dependency should be added to maven and changes in application.properties are required.  Edit the following if required;

```sh
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=***
spring.datasource.password=***
```

-----------------------------
## Build

Backend application -> swe573
Frontend application -> swe573-ui

Both should be built and run separately.

### Build Backend

Maven builds the application and creates the build result in the target folder.
```sh
 $ mvn install
```
To ensure that the build target is removed before a new build, clean target should be added.
```sh
 $ mvn clean install
```

By default, Maven checks online if the dependencies have been changed. To use local repository, it is possible to use -o to tell Maven to work offline.

```sh
 $ mvn -o clean install
```
To create an executable JAR file  of project, package should be used.
```sh
 $  mvn clean package
```
 
To run the test phases of the Maven life cycle, test should be used.
 ```sh
 $ mvn test
```

### Build Frontend
Run ng build to build the project. The build artifacts will be stored in the dist/ directory. Use the -prod flag for a production build.
```sh
$ ng build
```
Run ng test to execute the unit tests via Karma.
```sh
$ ng test
```
-----------------------------
## Run

### Run Backend
After creating an executable jar, run the application using java -jar.
```sh
$ java -jar target/swe573-0.0.1-SNAPSHOT.jar
```
It is also possible to run it with maven plugin.
```sh
$  mvn spring-boot:run
```

### Run Frontend
Run ng serve for a dev server. Navigate to http://localhost:4200/. The app will automatically reload if you change any of the source files.
```sh
$ ng serve
```


----------------------------------------

### Todos

 - Write More Tests
 - Refactor code
 - Improve usability
 - Improve security

