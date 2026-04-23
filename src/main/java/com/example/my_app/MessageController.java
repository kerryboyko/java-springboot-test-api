package com.example.my_app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.*;

/* This is intentionally not production-safe:

* Not thread-safe (two requests at once could collide)
* Data disappears on restart
* No validation

Controllers should handle the HTTP, services should handle the logic. 
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Message> create(@Valid @RequestBody Message message) {
        Message created = service.create(message);
        return ResponseEntity.status(201).body(created);
    }

    // READ ALL
    @GetMapping
    public Collection<Message> getAll() {
        return service.getAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Message> getOne(@PathVariable Long id) {
        return service.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity
                        .status(404)
                        .body(new Message(null, "Message not found")));
        // N.B.: .map (ResponseEntity::ok) is eqiv to .map(message ->
        // ResponseEntity.ok(message)). Unlike JS, you can't just do
        // .map(ResponseEntity.ok) because Java doesn't treat methods as valid values.
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}