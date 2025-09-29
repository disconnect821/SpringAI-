package com.example.springAI.SpringAI.service;

import com.example.springAI.SpringAI.chatResponse.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Map;
@Component
public class ChatServiceImplementation  implements  ChatService{

    @Autowired
    public ChatResponse chatResponse;

    @Override
    public String chat(String message) {
        return "";
    }

    //Prompt Templating

    //With this method we can use promptTemplate for dynamic values.
    public String generateContentUsingPromptTemplate(){

        PromptTemplate template = PromptTemplate.builder().template("What is {techName}").build();

        //We can set the values dynamic
        String renderedMessage = template.render(Map.of("techName","Spring"));

        Prompt prompt = new Prompt(renderedMessage);

        return chatResponse.getResponse(prompt);
    }

    //System Prompts

    //SystemPromptTemplate
    public String generateContentUsingSystemPromptTemplate(){

        SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate.builder().template("You are an Expert in Java").build();

        Message systemRenderedMessage = systemPromptTemplate.createMessage();

        PromptTemplate template = PromptTemplate.builder().template("What is {techName}").build();

        //We can set the values dynamic
        Message renderedMessage = template.createMessage(Map.of("techName", "Spring"));

        Prompt prompt = new Prompt(systemRenderedMessage,renderedMessage);

        return chatResponse.getResponse(prompt);

    }

    //Generating Streams

    public Flux<String> generateResponseInStream(String query) {
        return chatResponse.streamResponse(query);
    }

    public Flux<String> generateResponseInStreamFromVector(String query) {
        return chatResponse.streamResponseFromVector(query);
    }

    public String generateNormalResponse(String query){
        return chatResponse.generateResponse(query);
    }
}
