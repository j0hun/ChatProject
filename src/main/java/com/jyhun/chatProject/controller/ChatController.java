package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.ChatDTO;
import com.jyhun.chatProject.service.ChatService;
import com.jyhun.chatProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;
    private final MemberService memberService;

    // /pub
    @MessageMapping("/send/room")
    public void identifiedChat(@Payload ChatDTO chatDTO) {
        log.info(chatDTO.toString());
        Long memberId = chatDTO.getSender();
        Long roomId = chatDTO.getReceiver();
        chatService.addChat(chatDTO, roomId, memberId);
        String destination = "/sub/" + roomId;
        simpMessagingTemplate.convertAndSend(destination, chatDTO);
    }

    @MessageMapping("/enterMember")
    public void enterMember(@Payload ChatDTO chatDTO){
        log.info(chatDTO.toString());
        Long roomId = chatDTO.getReceiver();
        String destination = "/sub/" + roomId;
        chatDTO.setMessage("입장하셨습니다.");
        simpMessagingTemplate.convertAndSend(destination, chatDTO);
    }

    @MessageMapping("/leaveMember")
    public void leaveMember(@Payload ChatDTO chatDTO){
        log.info(chatDTO.toString());
        Long roomId = chatDTO.getReceiver();
        String destination = "/sub/" + roomId;
        chatDTO.setMessage("퇴장하셨습니다.");
        simpMessagingTemplate.convertAndSend(destination, chatDTO);
    }

}