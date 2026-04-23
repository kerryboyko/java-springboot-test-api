package com.example.my_app;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/*
Simple DTO that Spring will serialize to JSON in HTTP responses
   {
     "text": <the message text>
   }

   Spring quietly does Java Object -> JSON. 

   UPDATE: with @Entity, we're turning this into an entity. 
*/

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Text must not be empty")
    private String text;

    // default constructor, required for JSON serialization. 
    public Message() {}

    public Message(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

/* What's going on with @NotBlank

// OLD WAY: 
public Message(String text) {
    if (text == null || text.trim().isEmpty()) {
        throw new IllegalArgumentException("Text must not be empty");
    }
    this.text = text;
}

The logic lives in the constructor, throws immediately, and is coupled to object *creation*.

// NEW HOTNESS
    @NotBlank(message = "Text must not be empty")
    private String text;

The rule lives on the *field*, enforcement happens *externally* and is reusable across contexts.

*/