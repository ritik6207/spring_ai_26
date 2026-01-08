package com.spring.ai.firstproject.controller;

import com.spring.ai.firstproject.serviceImpl.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ChatController {

    @Autowired
    private ChatServiceImpl chatService;

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String query) {
        return ResponseEntity.ok(chatService.chat(query));
    }

}
