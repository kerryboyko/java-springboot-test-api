package com.example.my_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + "/messages";
    }

    @Test
    void createAndRetrieveMessage_fullFlow() {
        // Create request
        Message request = new Message(null, "Integration Test Message");

        ResponseEntity<Message> postResponse = restTemplate.postForEntity(baseUrl(), request, Message.class);

        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postResponse.getBody()).isNotNull();

        Long id = postResponse.getBody().getId();

        // Retrieve it
        ResponseEntity<Message> getResponse = restTemplate.getForEntity(baseUrl() + "/" + id, Message.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getText())
                .isEqualTo("Integration Test Message");
    }

    @Test
    void createMessage_returns400_whenInvalid() {
        Message badRequest = new Message(null, "");

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl(), badRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}