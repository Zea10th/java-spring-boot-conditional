package com.example.conditional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class DevApplicationTests {
    public static final String IMAGE_NAME = "devapp:latest";
    public static final int PORT = 8080;
    @Autowired
    TestRestTemplate restTemplate;
    private static final GenericContainer<?> myApp = new GenericContainer<>(IMAGE_NAME)
            .withExposedPorts(PORT);

    @BeforeAll
    public static void setUp() {
        myApp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(
                "http://localhost:" + myApp.getMappedPort(PORT), String.class
        );
        System.out.println(forEntity.getBody());
    }

}
