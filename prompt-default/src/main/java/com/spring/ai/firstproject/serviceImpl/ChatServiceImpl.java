package com.spring.ai.firstproject.serviceImpl;

import com.spring.ai.firstproject.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String chat(String query) {
//        For passing specific config AI
//        Prompt prompt = new Prompt(query, OpenAiChatOptions.builder()
//                .model("gpt-4o-mini")
//                .temperature(0.3)
//                .maxTokens(100)
//                .build());

        Prompt prompt = new Prompt(query);
        var content = this.chatClient.prompt(prompt).call().content();
        return content;
    }
}
