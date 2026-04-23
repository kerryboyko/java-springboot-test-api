package com.example.my_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public Message home() {
        return new Message(null, "Hello, Kerry Ann. Server is alive.");
    }

    @GetMapping("/helloworld")
    public Message helloPage() {
        return new Message(null, "Hello World!");
    }
    @PostMapping("/echo")
    public Message echo(@RequestBody Message message){
        return message;
    }
}