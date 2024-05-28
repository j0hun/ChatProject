package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.entity.ChatMessage;
import com.jyhun.chatProject.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/{chatRoomId}")
    public void chat(@DestinationVariable Long chatRoomId, @Payload ChatMessage chatMessage) {
        String destination = "/sub/" + chatRoomId;
        chatMessageService.addChatMessage(chatMessage);
        simpMessagingTemplate.convertAndSend(destination, chatMessage);
    }

    @GetMapping("/chat/room/{roomId}")
    public String viewChat(@PathVariable Long roomId, Model model) {
        model.addAttribute("roomId",roomId);
        return "chat";
    }

}