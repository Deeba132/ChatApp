package com.example.demo;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chats")
@SendTo("/topics")
public class ChatController {
    static String chatsy(String msg){
        return msg;
    }
}
