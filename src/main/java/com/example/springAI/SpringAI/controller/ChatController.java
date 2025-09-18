package com.example.springAI.SpringAI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

//    private OpenAiChatModel openAiChatModel;

//    public PromptController(OpenAiChatModel openAiChatModel){
//        this.openAiChatModel = openAiChatModel;
//    }

    private final ChatClient chatClient;

    ChatMemory chatMemory = MessageWindowChatMemory.builder().build();

    public ChatController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor
                        .builder(chatMemory)
                        .build())
                .build();
    }

    @GetMapping("{prompt}")
    public String getAnswer(@PathVariable String prompt){

//        call returns chatResponse.
//        return chatClient.prompt()
//                .user(prompt)
//                .call()
//                .content();

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}
