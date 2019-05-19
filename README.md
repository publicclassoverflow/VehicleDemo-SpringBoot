# Vehicle Entity RESTful Web Service Demo

## Usage

- Clone the project from [https://github.com/publicclassoverflow/VehicleDemo-SpringBoot.git](https://github.com/publicclassoverflow/VehicleDemo-SpringBoot.git)
- Run src/main/java/com.coollime.vehicle/VehicleDemoApplication.java
- Play with endpoint at `http://localhost:8080/vehicles`
- Alternatively, the service has been deployed to *heroku* at 
  - [https://mysterious-woodland-24939.herokuapp.com/vehicles](https://mysterious-woodland-24939.herokuapp.com/vehicles)

## Requirements

- Implement a RESTful web service that performs CRUD operations (Create, Read, Update, and Delete) for a
  Vehicle entity.

- A Vehicle is a simple object defined as follows:

  - ```java
    public class Vehicle {
      public int Id { get; set; }
      public int Year { get; set; }
      public String make { get; set; }
      public String model { get; set; }
    }
    ```

- The RESTful service must implement the following routes:

  - GET vehicles
  - GET vehicles/{id}
  - POST vehicles
  - PUT vehicles
  - DELETEvehicles/{id}

- A working implementation of this service can be found [here](https://estimate-dev.mymitchell.com/codingchallenge/v1/help).

- Additionally any solution must employ the following:

  - Usage of either C# or Java. C# is preferred.
  - Some form of automated testing.
  - Some form of in-memory persistence of created vehicle objects.
  - Function properly with the provided test web client.

- The test client can be accessed [here](https://estimate-dev.mymitchell.com/codingchallenge/v1/index.html#/) (please use [Chrome](https://www.google.com/intl/en/chrome/)). You will need to change the service URL to the
  URL of your hosted service when you are ready to test. Keep in mind that your service will most likely be
  using the non-secure HTTP protocol (and the test client is served over HTTPS which is secure) so you
  might need to allow unsafe scripts to execute on the web page to test your service. You can do this for
  Chromewith the [provided steps](https://superuser.com/questions/487748/how-to-allow-chrome-browser-to-load-insecure-content/487772#487772).

## Optional

- Got all the required stuff nailed down and want to enhance your service a little further? Here are some
  additional features you can add to make the service more interesting!
  - Add validation to your service.
    - Vehicles must have a non-null / non-empty make and model specified, and the year must be
      between 1950 and 2050.
  - Add filtering to your service.
    - The GET vehicles route should support filtering vehicles based on one or more vehicle properties.
      (EX: retrieving all vehicles where the 'Make' is 'Toyota')
  - Write an example client for your service!
    - Client should leverage AngularJS. (1.x or 2.0)
    - Any other libraries used are entirely up to you!

## Evaluation

Your implementation will be evaluated on the following criteria:

1. Whether or not the service implemented the required routes successfully.
2. The maintainability / readability of the service's code.
3. The flexibility of the service’s code. How well does the code base adapt to changing requirements?
4. The testability of the service's code.
5. The re-usability of the service's code. Can facets of the service's code be used for other services or
   applications?

**Good luck!**

------

## Notes

### Spring Boot

- Setup

  - <https://start.spring.io/>
  - Dependencies
    - Web, JPA, h2, JSON, JUnit5
  - Useful tutorials followed
    - <https://www.baeldung.com/spring-boot>
    - <https://www.baeldung.com/spring-boot-testing>

- Testing

  - *No Runnable methods error*

    - Solved by excluding JUnit 4 and only including JUnit 5 dependencies
    - <https://stackoverflow.com/questions/46214928/no-runnable-methods-error-when-migrating-from-junit4-to-junit5>
    - If you are using JUnit 4, don’t forget to also add `@RunWith(SpringRunner.class)` to your test, otherwise the annotations will be ignored. If you are using JUnit 5, there’s no need to add the equivalent `@ExtendWith(SpringExtension.class)` as `@SpringBootTest` and the other `@…Test`annotations are already annotated with it.

  - Vehicle.id is *auto-incremented*, so it interfered with testing

    - Solved by using **@DirtiesContext** annotation

      - ```
        @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
        ```

        - By doing this, the application creates a new in-memory database after each test.

      - <https://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-resetting-auto-increment-columns-before-each-test-method/>

      - > This looks clean but unfortunately it can destroy the performance of our integration test suite because it creates a new application context before each test method is invoked. That is why **we should not use the @DirtiesContext annotation unless it is ABSOLUTELY NECESSARY**.
        >
        > However, if our application has only a small number of integration tests, the performance penalty caused by the *@DirtiesContext* annotation might be tolerable. We shouldn’t abandon this solution just because it makes our tests slower. Sometimes this is acceptable, and if this is the case, using the *@DirtiesContext* annotation is a good solution.

      - Which is just my case

  - Controller test

    - - By default, `@SpringBootTest` will not start a server. You can use the `webEnvironment` attribute of `@SpringBootTest` to further refine how your tests run:

        - `MOCK`(Default) : Loads a web `ApplicationContext` and provides a mock web environment. Embedded servers are not started when using this annotation. If a web environment is not available on your classpath, this mode transparently falls back to creating a regular non-web `ApplicationContext`. It can be used in conjunction with [`@AutoConfigureMockMvc` or `@AutoConfigureWebTestClient`](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-with-mock-environment) for mock-based testing of your web application.
        - `RANDOM_PORT`: Loads a `WebServerApplicationContext` and provides a real web environment. Embedded servers are started and listen on a random port.
        - `DEFINED_PORT`: Loads a `WebServerApplicationContext` and provides a real web environment. Embedded servers are started and listen on a defined port (from your `application.properties`) or on the default port of `8080`.
        - `NONE`: Loads an `ApplicationContext` by using `SpringApplication` but does not provide *any* web environment (mock or otherwise).

        | ![[Note]](https://docs.spring.io/spring-boot/docs/current/reference/html/images/note.png) |
        | ------------------------------------------------------------ |
        | If your test is `@Transactional`, it rolls back the transaction at the end of each test method by default. However, as using this arrangement with either `RANDOM_PORT` or `DEFINED_PORT` implicitly provides a real servlet environment, the HTTP client and server run in separate threads and, thus, in separate transactions. Any transaction initiated on the server does not roll back in this case. |

        | ![[Note]](https://docs.spring.io/spring-boot/docs/current/reference/html/images/note.png) |
        | ------------------------------------------------------------ |
        | `@SpringBootTest` with `webEnvironment = WebEnvironment.RANDOM_PORT` will also start the management server on a separate random port if your application uses a different port for the management server. |

    - <https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html>

    - POST

      - <https://www.baeldung.com/spring-5-webclient>

      - ```
        WebTestClient
          .bindToServer()
            .baseUrl("http://localhost:8080")
            .build()
            .post()
            .uri("/resource")
          .exchange()
            .expectStatus().isCreated()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody().isEmpty();
        ```