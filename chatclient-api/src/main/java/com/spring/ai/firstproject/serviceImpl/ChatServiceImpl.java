package com.spring.ai.firstproject.serviceImpl;

import com.spring.ai.firstproject.config.AiConfig;
import com.spring.ai.firstproject.entity.Tute;
import com.spring.ai.firstproject.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Override
    public String chat(String query) {

//        String content = chatClient
//                .prompt()
//                .user(prompt)
//                .system("For AI Reevaluation")
//                .call()
//                .content();

//        String prompt = "Tell me about the Virat Kohli?";

        Prompt prompt1 = new Prompt(query);
        var content = chatClient
                .prompt(prompt1)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
        System.out.println(content);
        return content;
    }

    @Override
    public Tute chatW(String query) {
        Prompt prompt = new Prompt(query);
        Tute tute = chatClient.prompt(prompt).call().entity(Tute.class);
        return tute;
    }

    @Override
    public List<Tute> chatL(String query) {
        Prompt prompt = new Prompt(query);
        List<Tute> tutes =  chatClient.prompt(prompt).call().entity(new ParameterizedTypeReference<List<Tute>>() {});
        return tutes;
    }
}
