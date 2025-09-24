package com.example.springAI.SpringAI.controller;

import com.example.springAI.SpringAI.chatResponse.ChatResponse;
import com.example.springAI.SpringAI.service.ChatServiceImplementation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class PromptController {

    private final ChatResponse chatResponse;
    private final ChatServiceImplementation chatServiceImplementation;
    public PromptController(ChatResponse chatResponse, ChatServiceImplementation chatServiceImplementation) {
        this.chatResponse = chatResponse;
        this.chatServiceImplementation = chatServiceImplementation;
    }

    @GetMapping({"prompt/{userPrompt}"})
    public String getPromptAnswer(@PathVariable String userPrompt){
        String template = chatResponse.generatePromptFromModel(userPrompt);
        System.out.println(template);
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create();

        return chatResponse.getResponse(prompt);
    }

    @GetMapping({"promptTemplate"})
    public String getPromptTemplateResponse(){
        return chatServiceImplementation.generateContentUsingPromptTemplate();
    }

    @GetMapping({"SystemAndPromptTemplate"})
    public String getSystemAndPromptTemplateResponse(){
        return chatServiceImplementation.generateContentUsingSystemPromptTemplate();
    }
    @GetMapping({"stream-chat"})
    public Flux<String> getStreamChat(@RequestParam String query){
        return chatServiceImplementation.generateResponseInStream(query);
    }
    @GetMapping({"memoryChat"})
    public String getMemoryChat(@RequestParam String query){
        return chatServiceImplementation.generateNormalResponse(query);
    }
}
