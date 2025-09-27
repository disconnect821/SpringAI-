package com.example.springAI.SpringAI.controller;


import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class EmbeddingController {

    @Autowired
    private EmbeddingModel embeddingModel;

    @GetMapping("/embedding")
    public ResponseEntity<float[]> getEmbedding(@RequestParam String word){
        return new ResponseEntity<>(embeddingModel.embed(word), HttpStatus.OK);
    }

    //Cosine Similarity

    @GetMapping("/similarity")
    public ResponseEntity<Double> getSimilarity(@RequestParam String text1, @RequestParam String text2){
        float[] emebdding1 = embeddingModel.embed(text1);
        float[] emebdding2 = embeddingModel.embed(text2);

        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;

        for(int i = 0 ;i<emebdding1.length;i++){
            dotProduct += emebdding1[i]*emebdding2[i];
            norm1 += Math.pow(emebdding1[i],2);
            norm2 += Math.pow(emebdding2[i],2);
        }

        double result = dotProduct / ((Math.sqrt(norm1) * Math.sqrt(norm2)));

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/similarity/percentage")
    public ResponseEntity<Double> getSimilarityPercentage(@RequestParam String text1, @RequestParam String text2){
        float[] emebdding1 = embeddingModel.embed(text1);
        float[] emebdding2 = embeddingModel.embed(text2);

        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;

        for(int i = 0 ;i<emebdding1.length;i++){
            dotProduct += emebdding1[i]*emebdding2[i];
            norm1 += Math.pow(emebdding1[i],2);
            norm2 += Math.pow(emebdding2[i],2);
        }

        double result = dotProduct / ((Math.sqrt(norm1) * Math.sqrt(norm2)));

        return new ResponseEntity<>(result*100, HttpStatus.OK);

    }
}
