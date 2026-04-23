package com.example.my_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService service;

    @Test
    void getMessageById_returns200_whenFound() throws Exception {
        Message message = new Message(1L, "Hello");

        when(service.getOne(1L)).thenReturn(Optional.of(message));

        mockMvc.perform(get("/messages/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Hello"));
    }

    @Test
    void getMessageById_returns404_whenNotFound() throws Exception {
        when(service.getOne(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/messages/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createMessage_returns201_whenValid() throws Exception {
        Message saved = new Message(1L, "Hello");

        when(service.create(org.mockito.ArgumentMatchers.any()))
                .thenReturn(saved);

        mockMvc.perform(post("/messages")
                .contentType("application/json")
                .content("{\"text\":\"Hello\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Hello"));
    }

    @Test
    void createMessage_returns400_whenInvalid() throws Exception {
        mockMvc.perform(post("/messages")
                .contentType("application/json")
                .content("{\"text\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}