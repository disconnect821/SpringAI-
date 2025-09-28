package com.example.springAI.SpringAI.controller;

import com.example.springAI.SpringAI.chatResponse.ChatResponse;
import com.example.springAI.SpringAI.service.ChatServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/api")
public class RAGvectorController {
    private final ChatServiceImplementation chatServiceImplementation;

    public RAGvectorController(ChatResponse chatResponse, ChatServiceImplementation chatServiceImplementation) {
        this.chatServiceImplementation = chatServiceImplementation;
    }

    @GetMapping({"/vector"})
    public Flux<String> getStreamChat(@RequestParam String query){
        return chatServiceImplementation.generateResponseInStream(query);
    }
}
