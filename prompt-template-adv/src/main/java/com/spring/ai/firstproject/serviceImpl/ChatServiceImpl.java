package com.spring.ai.firstproject.serviceImpl;

import com.spring.ai.firstproject.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.defaultOptions(OpenAiChatOptions.builder()
                .maxTokens(200).build()).build();
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

    public String chatTemplate() {
        /*
//        First Step to create a prompt template with template string and build it
        PromptTemplate strTemplate = PromptTemplate.builder()
                .template("What is {techName}? tell me also about {techExample}?")
                .build();

//       Second Step to Render the prompt template
        String renderedMessage = strTemplate.render(Map.of(
                "techName", "Spring",
                "techExample", "Spring exceptions"
        ));
//        Create a prompt
        Prompt prompt = new Prompt(renderedMessage);
        var content =  this.chatClient.prompt(prompt).call().content();
*/
//        for system prompt template
        var systemPromptTemplate = SystemPromptTemplate.builder().template("You are a helpful assistant. You are an expert in coding and programming.").build();
        var systemMessage = systemPromptTemplate.createMessage();
        var userPromptTemplate = PromptTemplate.builder().template("What is {techName}? tell me also about {techExample}?").build();
        var userPromptMessage = userPromptTemplate.createMessage(Map.of(
                "techName", "Spring",
                "techExample", "Spring exceptions"
        ));
        Prompt prompt = new Prompt(systemMessage,userPromptMessage);
        var content = this.chatClient.prompt(prompt).call().content();
        return content;
    }
}
