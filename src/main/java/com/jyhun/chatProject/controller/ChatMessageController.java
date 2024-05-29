package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.ChatMessageDTO;
import com.jyhun.chatProject.service.ChatMessageService;
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
public class ChatMessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;
    private final MemberService memberService;

    // /pub
    @MessageMapping("/send/room")
    public void identifiedChat(@Payload ChatMessageDTO chatMessageDTO) {
        log.info(chatMessageDTO.toString());
        Long memberId = chatMessageDTO.getSender();
        Long chatRoomId = chatMessageDTO.getReceiver();
        chatMessageService.addChatMessage(chatMessageDTO, chatRoomId, memberId);
        String destination = "/sub/" + chatRoomId;
        simpMessagingTemplate.convertAndSend(destination, chatMessageDTO);
    }

    @MessageMapping("/enterMember")
    public void enterMember(@Payload ChatMessageDTO chatMessageDTO){
        log.info(chatMessageDTO.toString());
        Long chatRoomId = chatMessageDTO.getReceiver();
        String destination = "/sub/" + chatRoomId;
        chatMessageDTO.setMessage("입장하셨습니다.");
        simpMessagingTemplate.convertAndSend(destination, chatMessageDTO);
    }

    @MessageMapping("/leaveMember")
    public void leaveMember(@Payload ChatMessageDTO chatMessageDTO){
        log.info(chatMessageDTO.toString());
        Long chatRoomId = chatMessageDTO.getReceiver();
        String destination = "/sub/" + chatRoomId;
        chatMessageDTO.setMessage("퇴장하셨습니다.");
        simpMessagingTemplate.convertAndSend(destination, chatMessageDTO);
    }

}