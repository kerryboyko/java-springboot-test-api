package com.example.my_app;

/*
Simple DTO that Spring will serialize to JSON in HTTP responses
   {
     "text": <the message text>
   }

   Spring quietly does Java Object -> JSON. 
*/

public class Message {
    private Long id;
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