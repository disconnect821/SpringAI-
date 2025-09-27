package com.example.springAI.SpringAI.applicationConfiguration;

import com.example.springAI.SpringAI.advisors.TokenPrintAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    private final Logger logger = LoggerFactory.getLogger(ChatClientConfig.class);

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
//    @Bean
//    public ChatClient chatClient (ChatClient.Builder chatClientBuilder){
//        System.out.println("Creating ChatClient Bean here");
//        return chatClientBuilder
//                .defaultAdvisors(new TokenPrintAdvisor())
//                .defaultOptions(OpenAiChatOptions.builder()
//                        .maxTokens(200)
//                        .build())
//                .build();
//    }


    //Implementing Chat memory

    //ChatMemory is an interface and implemented by MessageWindowChatMemory (default)


    //With the chatMemory bean, we can explicitly set the chatMemoryRepository to chatMemory with message limits
    // when custom chatMemoryRepo is created by user.

//    @Bean
//    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
//        return MessageWindowChatMemory.builder()
//                .chatMemoryRepository(jdbcChatMemoryRepository)
//                .maxMessages(30)
//                .build();
//    }

    @Bean
    public ChatClient chatClient (ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, ChatMemoryRepository chatMemoryRepository){

        logger.info("Creating ChatClient Bean here");
        logger.info("Getting ChatMemory bean : " + chatMemory.getClass().getName());
        logger.info("Getting ChatMemory bean : " + chatMemoryRepository.getClass().getName());

        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();

        return chatClientBuilder
                .defaultAdvisors(new TokenPrintAdvisor(), messageChatMemoryAdvisor )
                .defaultOptions(OpenAiChatOptions.builder()
                        .maxTokens(200)
                        .build())
                .build();
    }


}
