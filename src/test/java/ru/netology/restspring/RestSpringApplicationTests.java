package ru.netology.restspring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestSpringApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private final GenericContainer<?> appDev = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    @Container
    private final GenericContainer<?> appProd = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeEach
    void setUp() {
        appDev.start();
        appProd.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntity = restTemplate
                .getForEntity("http://localhost:" + appDev.getMappedPort(8080) + "/api/auth/hello", String.class);

        System.out.println(forEntity.getBody());
        Assertions.assertEquals("Hello - version 1.0", forEntity.getBody());
    }

    @Test
    void contextLoadsTwo() {
        ResponseEntity<String> forEntitySecond = restTemplate
                .getForEntity("http://localhost:" + appProd.getMappedPort(8081) + "/api/auth/hello", String.class);

        System.out.println(forEntitySecond.getBody());
        Assertions.assertEquals("Hello - version 2.0", forEntitySecond.getBody());
    }



}
