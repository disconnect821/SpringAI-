package com.example.springAI.SpringAI.controller;

import com.example.springAI.SpringAI.service.ChatServiceImplementation;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


import java.util.List;

@RestController
@RequestMapping("/api")
public class RAGvectorController {
    private final ChatServiceImplementation chatServiceImplementation;
    private final VectorStore vectorStore;

    public RAGvectorController(VectorStore vectorStore, ChatServiceImplementation chatServiceImplementation) {
        this.chatServiceImplementation = chatServiceImplementation;
        this.vectorStore = vectorStore;
    }

    @GetMapping({"/vector"})
    public Flux<String> getStreamChat(@RequestParam String query){
        return chatServiceImplementation.generateResponseInStreamFromVector(query);
    }

    @GetMapping({"/similarity/search"})
    public List<Document> getDocument(@RequestParam String query){
        return vectorStore.similaritySearch(query);
    }
}
