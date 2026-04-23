package com.example.my_app;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {
    // final means this variable will always POINT to the same object.
    // you CANNOT reassign it. It's mutable, but it locks the REFERENCE, not the
    // OBJECT.
    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public Message create(Message message) {
        return repo.save(message);
    }

    public Collection<Message> getAll() {
        return repo.findAll();
    }

    public Optional<Message> getOne(Long id) {
        return repo.findById(id);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id))
            return false;
        repo.deleteById(id);
        return true;
    }
}