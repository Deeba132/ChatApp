package com.example.demo;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/messages")//server to client
@SendTo("/topics")//client to controller
public class ChatController {
    static String chatsy(String msg){
        return msg;
    }
}
