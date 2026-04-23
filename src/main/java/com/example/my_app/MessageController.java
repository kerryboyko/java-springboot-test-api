package com.example.my_app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/* This is intentionally not production-safe:

* Not thread-safe (two requests at once could collide)
* Data disappears on restart
* No validation
* No error handling (missing IDs return null)
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final Map<Long, Message> messages = new HashMap<>();
    private Long nextId = 1L;

    // CREATE
    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setId(nextId++);
        messages.put(message.getId(), message);
        return message;
    }

    // READ ALL
    @GetMapping
    public Collection<Message> getAll() {
        return messages.values();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Message> getOne(@PathVariable Long id) {
        Message message = messages.get(id);

        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(message);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        messages.remove(id);
    }
}