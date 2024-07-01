package com.forohub.forohub.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicController {


    @GetMapping
    public String getTopic() {
        return "Hello World";
    }


}
