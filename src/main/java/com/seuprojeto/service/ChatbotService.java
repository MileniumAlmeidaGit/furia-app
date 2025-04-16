package com.seuprojeto.service;

import com.seuprojeto.model.ChatMessage;
import com.seuprojeto.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage processQuestion(String question) {
        String answer = "Esta é a resposta para: " + question;
        ChatMessage msg = new ChatMessage();
        msg.setQuestion(question);
        msg.setAnswer(answer);


        return chatMessageRepository.save(msg);
    }
}