package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.ChatRoomDTO;
import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/chat/room")
    public String viewRoom(Model model) {
        List<ChatRoom> chatRoomList = chatRoomService.findChatRoomList();
        model.addAttribute("rooms",chatRoomList);
        return "room";
    }

    @PostMapping("/chat/room")
    @ResponseBody
    public ResponseEntity<Void> postRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        chatRoomService.addChatRoom(chatRoomDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
