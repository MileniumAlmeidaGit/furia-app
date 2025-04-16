package com.seuprojeto.controller;

import com.seuprojeto.model.ChatMessage;
import com.seuprojeto.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping
    public ChatMessage askQuestion(@RequestBody QuestionRequest request) {
        return chatbotService.processQuestion(request.getQuestion());
    }
}

// DTO para receber a pergunta
class QuestionRequest {
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}