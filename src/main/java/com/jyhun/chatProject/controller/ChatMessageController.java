package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.ChatMessageDTO;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.service.ChatMessageService;
import com.jyhun.chatProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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
    @MessageMapping("/{chatRoomId}")
    public void identifiedChat(@DestinationVariable Long chatRoomId, @Payload ChatMessageDTO chatMessageDTO) {
        String name = chatMessageDTO.getName();
        Member member = memberService.findMemberByName(name);
        String destination = "/sub/" + chatRoomId;
        chatMessageService.addChatMessage(chatMessageDTO, chatRoomId, member.getEmail());
        log.info(chatMessageDTO.toString());
        simpMessagingTemplate.convertAndSend(destination, chatMessageDTO);
    }


}