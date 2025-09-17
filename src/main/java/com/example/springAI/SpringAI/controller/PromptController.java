package com.example.springAI.SpringAI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptController {

//    private OpenAiChatModel openAiChatModel;
//
//    public PromptController(OpenAiChatModel openAiChatModel){
//        this.openAiChatModel = openAiChatModel;
//    }

    private final ChatClient chatClient;

    public PromptController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/api/{prompt}")
    public String getAnswer(@PathVariable String prompt){
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
