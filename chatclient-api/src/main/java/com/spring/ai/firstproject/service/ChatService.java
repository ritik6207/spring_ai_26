package com.spring.ai.firstproject.service;

import com.spring.ai.firstproject.entity.Tute;

import java.util.List;

public interface ChatService {
    String chat(String query);
    Tute chatW(String query);
    List<Tute> chatL(String query);
}
