package com.jyhun.chatProject.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/send")
    public void greeting(@Payload ChatMessage chatMessage) {
        simpMessagingTemplate.convertAndSend("/topic/1",chatMessage);
    }

}