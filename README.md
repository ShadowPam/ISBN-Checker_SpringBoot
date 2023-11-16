# ISBN-checker as part of work sample


## Functionality
The program should utilize a web framework to build a backend api that can handle ISBN 10 and 13 validation.

Stack used:
1. Java
2. Spring Boot framework
3. JUnit with SpringBootTest for testing

## Running/Installation
1. Have Java 17 installed
2. Make sure your JAVA HOME environment variable is associated with your JDK path

#### For testing
1. Navigate to root folder and execute `mvnw test` to execute unit tests.
2. Optional testing: Utilize postman to send GET Request to API path: `/api/isbncheck/{isbnnumber}`
3. Optional optional testing: Load up the webpage on `localhost:8080` and execute GET requests using the URL line.

#### For running
1. Navigate to root folder and execute `mvnw spring-boot:run`.
2. Optional: If you are using an IDE that has Spring Boot compatability, run the application by pressing the "Run" button.