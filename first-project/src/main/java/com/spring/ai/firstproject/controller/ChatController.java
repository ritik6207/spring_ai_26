package com.spring.ai.firstproject.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ChatController {

    private ChatClient openAiChatClient;
    private ChatClient ollamaChatClient;

    public ChatController(@Qualifier("openAiChatClient") ChatClient openAiChatClient, @Qualifier("openAiChatClient") ChatClient ollamaChatClient) {
        this.openAiChatClient = openAiChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    //    Using ChatClient
    /*
    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    */

//    With the help of ChatModel
//    public ChatController(OpenAiChatModel openAiChatModel, OllamaChatModel ollamaChatModel) {
//        this.openAiChatClient = ChatClient.builder(openAiChatModel).build();
//    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {
        var resultResponse = this.openAiChatClient
                .prompt(q)
                .call()
                .content();
        return ResponseEntity.ok(resultResponse);
    }
}
