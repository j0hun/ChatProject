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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;
    private final MemberService memberService;

    @MessageMapping("/{chatRoomId}")
    public void chat(@DestinationVariable Long chatRoomId, @Payload ChatMessageDTO chatMessageDTO) {
        String name = chatMessageDTO.getName();
        Member member = memberService.findMemberByName(name);
        String destination = "/sub/" + chatRoomId;
        chatMessageService.addChatMessage(chatMessageDTO,chatRoomId,member.getEmail());
        log.info(chatMessageDTO.toString());
        simpMessagingTemplate.convertAndSend(destination, chatMessageDTO);
    }

    @GetMapping("/chat/room/{roomId}")
    public String viewChat(@PathVariable Long roomId, Model model, Principal principal) {
        model.addAttribute("roomId",roomId);
        String email = principal.getName();
        Member member = memberService.findMemberByEmail(email);
        model.addAttribute("member",member);
        return "chat/chat";
    }

}