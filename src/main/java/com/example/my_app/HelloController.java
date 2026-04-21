package com.example.my_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hello, Kerry Ann. Server is alive.";
    }
    @GetMapping("/helloworld")
    public String helloPage() {
        return "Hello World!";
    }
}