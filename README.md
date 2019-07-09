# SPO Coding Challenge

This is a workforce application for a cleaning service. The application distributes optimal number of senior and junior cleaners for the cleaning of the structures.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.spo.SpoApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

By default application is running locally on http://localhost:8080

## REST Endpoint

The application is accessible through REST API on the endpoint http://localhost:8080/distribute
The format of the incomming JSON should be as follows:
```shell
{
	"rooms": ["35", "21", "17"],
	"senior": "10",
	"junior": "6"
}
```
Where 
  - rooms is an array of rooms (int) for every structure
  - senior is the cleaning capacity of the Senior Cleaner (int)
  - junior is the cleaning capacity of the Junior Cleaner (int)
  
Application returns an array of maps which includes the optimal number of Juniors and Seniors for every structure.

```shell
[
    {
        "senior": 3,
        "junior": 1
    },
    {
        "senior": 1,
        "junior": 2
    },
    {
        "senior": 2,
        "junior": 0
    }
]
```

## Supported Validations

None of the structures can have more than 100 rooms.

Senior Cleaners have a higher work capacity than Junior Cleaners.


