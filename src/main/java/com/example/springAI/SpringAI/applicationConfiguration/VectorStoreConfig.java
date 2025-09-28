package com.example.springAI.SpringAI.applicationConfiguration;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class VectorStoreConfig {

    //Since pgVector is an external DB, so JdbcTemplate is required to pass in builder

    @Bean
    public VectorStore getVectorStore (JdbcTemplate jdbcTemplate, EmbeddingModel embeddingModel){
        return PgVectorStore.builder(jdbcTemplate,embeddingModel)
                .dimensions(1536)
                .build();
    }
}
