package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.ChatDTO;
import com.jyhun.chatProject.entity.MemberRoom;
import com.jyhun.chatProject.service.ChatService;
import com.jyhun.chatProject.service.MemberRoomService;
import com.jyhun.chatProject.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;
    private final MemberRoomService memberRoomService;
    private final RoomService roomService;

    // /pub
    @MessageMapping("/send/room")
    public void sendChat(@Payload ChatDTO chatDTO) {
        log.info(chatDTO.toString());
        Long memberId = chatDTO.getSender();
        Long roomId = chatDTO.getReceiver();
        chatService.addChat(chatDTO, roomId, memberId);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + roomId, chatDTO);
    }

    @MessageMapping("/enterMember")
    public void enterMember(@Payload ChatDTO chatDTO){
        log.info(chatDTO.toString());
        Long roomId = chatDTO.getReceiver();
        if (memberRoomService.checkMemberRoom(chatDTO.getSender(), chatDTO.getReceiver())) {
            MemberRoom memberRoom = memberRoomService.findMemberRoom(chatDTO.getSender(), chatDTO.getReceiver());
            List<ChatDTO> chatDTOList = chatService.findChatListByRoomIdAndDate(chatDTO.getReceiver(),memberRoom.getDate());
            simpMessagingTemplate.convertAndSend("/sub/chat/" + roomId, chatDTOList);
        } else {
            memberRoomService.enter(chatDTO.getSender(), chatDTO.getReceiver());
            chatDTO.setMessage("입장하셨습니다.");
            simpMessagingTemplate.convertAndSend("/sub/chat/" + roomId, chatDTO);
        }
    }

    @MessageMapping("/leaveMember")
    public void leaveMember(@Payload ChatDTO chatDTO){
        log.info(chatDTO.toString());
        Long roomId = chatDTO.getReceiver();
        memberRoomService.leave(chatDTO.getSender(), chatDTO.getReceiver());
        chatDTO.setMessage("퇴장하셨습니다.");
        simpMessagingTemplate.convertAndSend("/sub/" + roomId, chatDTO);
    }

}