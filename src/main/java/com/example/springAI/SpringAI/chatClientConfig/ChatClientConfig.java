package com.example.springAI.SpringAI.chatClientConfig;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    // Here we can create our ChatClient bean without injecting using Builder in controller and services.
    // Also more that one chatClient can be created on the basis of chatModels.

    // Creating normal chatClient bean

    @Bean
    public ChatClient chatClient (ChatClient.Builder chatClientBuilder){
        System.out.println("Creating ChatClient Bean here");
        return chatClientBuilder
                .build();
    }

    // chatClient bean with default Advisors

}
