package com.spring.ai.firstproject.controller;

import com.spring.ai.firstproject.entity.Tute;
import com.spring.ai.firstproject.serviceImpl.ChatServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping
public class ChatController {

    private ChatServiceImpl chatService;

    public ChatController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {
        return ResponseEntity.ok(chatService.chat(q));
    }

    @GetMapping("/chatw")
    public ResponseEntity<Tute> chatW(@RequestParam(value = "q", required = true) String query) {
        return ResponseEntity.ok(chatService.chatW(query));
    }

    @GetMapping("/chatl")
    public ResponseEntity<List<Tute>> chatL(@RequestParam(value = "q", required = true) String query) {
        return ResponseEntity.ok(chatService.chatL(query));
    }
}
