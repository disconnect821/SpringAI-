package com.example.springAI.SpringAI.chatResponse;

import com.example.springAI.SpringAI.chatClientConfig.ChatClientConfig;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ChatResponse {

//    private final ChatClient chatClient;
//
//    ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
//
//    public ChatResponse(ChatClient.Builder chatClientBuilder){
//        this.chatClient = chatClientBuilder
//                .defaultAdvisors(MessageChatMemoryAdvisor
//                        .builder(chatMemory)
//                        .build())
//                .defaultOptions(
//                        OpenAiChatOptions.builder()
//                                .maxTokens(300)
//                                .build()
//                )
//                .build();
//    }

    private final ChatClient chatClient;

    //Fetching system message to avoid hardcoding
    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    public ChatResponse(ChatClient chatClient){
        this.chatClient = chatClient;
    }


    public String getResponse(Prompt prompt){
        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
    public String generatePromptFromModel(String prompt){
        return chatClient
                .prompt("Rephrase this " + prompt + " in one single prompt")
                .call()
                .content();
    }


    //System prompt is basically used to define rules, behaviour or identity to generate the accurate and expected results.
//    public String generatePromptFromModelWithSystemPrompt(String prompt){
//        return chatClient
//                .prompt()
//                .user(prompt)
//                .system("As a assitant | You are a java developer | Think like a designer")
//                .call()
//                .content();
//    }

    public Flux<String> streamResponse(String query) {
        return chatClient
                .prompt()
                .system(system -> system.text(systemMessage).param("version",8))
                .user(query)
                .stream()
                .content();
    }
}
