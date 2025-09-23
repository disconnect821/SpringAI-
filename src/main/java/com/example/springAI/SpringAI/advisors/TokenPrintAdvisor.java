package com.example.springAI.SpringAI.advisors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

public class TokenPrintAdvisor implements CallAdvisor, StreamAdvisor {

    private final Logger logger = LoggerFactory.getLogger(TokenPrintAdvisor.class);

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        logger.info("Custom TokenPrintAdvisor is called");
        logger.info("Request : " + chatClientRequest
                .prompt()
                .getContents());

        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        logger.info("Response received from the chatModel");
        logger.info("Total Tokens  : " + chatClientResponse
                .chatResponse()
                .getMetadata()
                .getUsage()
                .getTotalTokens());

        // total tokens = user token + completion token

        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        Flux<ChatClientResponse> chatClientResponseFlux = streamAdvisorChain.nextStream(chatClientRequest);
        return chatClientResponseFlux;
    }

    @Override
    public String getName() {
        return "TokenPrintAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
