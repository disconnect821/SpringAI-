package com.example.springAI.SpringAI.utility;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    public VectorStore vectorStore;

    public DataInitializer(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void initData(){
        TextReader textReader = new TextReader(new ClassPathResource("/productData/product.txt"));
//        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
//        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter(500,30,20,500,false);
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter(100,30,10,500,false);
        List<Document> documentList = tokenTextSplitter.split(textReader.get());

        vectorStore.add(documentList);
    }
}
