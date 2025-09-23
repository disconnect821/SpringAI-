package com.example.springAI.SpringAI.chatClientConfig;

import com.example.springAI.SpringAI.advisors.TokenPrintAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    // Here we can create our ChatClient bean without injecting using Builder in controller and services.
    // Also more that one chatClient can be created on the basis of chatModels.

    // Creating normal chatClient bean

//    @Bean
//    public ChatClient chatClient (ChatClient.Builder chatClientBuilder){
//        System.out.println("Creating ChatClient Bean here");
//        return chatClientBuilder
//                .build();
//    }

    // chatClient bean with default Advisors

//    @Bean
//    public ChatClient chatClient (ChatClient.Builder chatClientBuilder){
//        System.out.println("Creating ChatClient Bean here");
//        return chatClientBuilder
//                .defaultAdvisors(new SimpleLoggerAdvisor())
//                .build();
//    }

    //with custom advisor
    @Bean
    public ChatClient chatClient (ChatClient.Builder chatClientBuilder){
        System.out.println("Creating ChatClient Bean here");
        return chatClientBuilder
                .defaultAdvisors(new TokenPrintAdvisor())
                .defaultOptions(OpenAiChatOptions.builder()
                        .maxTokens(200)
                        .build())
                .build();
    }
}
