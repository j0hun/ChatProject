package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.ChatRoomRequestDTO;
import com.jyhun.chatProject.dto.ChatRoomResponseDTO;
import com.jyhun.chatProject.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/api/chat/room")
    public ResponseEntity<List<ChatRoomResponseDTO>> viewRoom(){
        List<ChatRoomResponseDTO> chatRoomList = chatRoomService.findChatRoomList();
        return new ResponseEntity<>(chatRoomList, HttpStatus.OK);
    }

    @GetMapping("api/chat/room/{roomId}")
    public ResponseEntity<ChatRoomResponseDTO> enterRoom(@PathVariable Long roomId) {
        ChatRoomResponseDTO chatRoom = chatRoomService.findChatRoom(roomId);
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

    @PostMapping("api/chat/room")
    public ResponseEntity<Void> postRoom(@RequestBody ChatRoomRequestDTO chatRoomRequestDTO, Principal principal) {
        String email = principal.getName();
        chatRoomService.addChatRoom(chatRoomRequestDTO,email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
