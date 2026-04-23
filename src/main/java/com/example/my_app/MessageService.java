package com.example.my_app;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {
    // final means this variable will always POINT to the same object.
    // you CANNOT reassign it. It's mutable, but it locks the REFERENCE, not the OBJECT.
    private final Map<Long, Message> messages = new HashMap<>();
    private Long nextId = 1L;

    public Message create(Message message) {
        message.setId(nextId++);
        messages.put(message.getId(), message);
        return message;
    }

    public Collection<Message> getAll() {
        return messages.values();
    }

    public Optional<Message> getOne(Long id) {
        return Optional.ofNullable(messages.get(id));
    }

    public boolean delete(Long id) {
        return messages.remove(id) != null;
    }
}