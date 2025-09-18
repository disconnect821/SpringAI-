package com.example.springAI.SpringAI.controller;

import com.example.springAI.SpringAI.controller.chatResponse.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PromptController {

    private final ChatResponse chatResponse;

    public PromptController(ChatResponse chatResponse) {
        this.chatResponse = chatResponse;
    }

    @GetMapping({"prompt/{userPrompt}"})
    public String getPromptAnswer(@PathVariable String userPrompt){
        String template = chatResponse.generatePromptFromModel(userPrompt);
        System.out.println(template);
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create();

        return chatResponse.getResponse(prompt);
    }
}
