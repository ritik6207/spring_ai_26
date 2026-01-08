package com.spring.ai.firstproject.serviceImpl;

import com.spring.ai.firstproject.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String chat(String query) {
        Prompt prompt = new  Prompt(query);
        String queryTemp = "As an expert in coding and programming. Always write program in java. Now reply for this question: {query}";
        var content = this.chatClient
                .prompt()
                .user(u -> u.text(queryTemp).param("query", query))
                .call()
                .content();
        return  content;
    }
}
