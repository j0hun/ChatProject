package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.RoomRequestDTO;
import com.jyhun.chatProject.dto.RoomResponseDTO;
import com.jyhun.chatProject.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/api/chat/room")
    public ResponseEntity<List<RoomResponseDTO>> viewRoom(){
        List<RoomResponseDTO> roomList = roomService.findRoomList();
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @GetMapping("api/chat/room/{roomId}")
    public ResponseEntity<RoomResponseDTO> enterRoom(@PathVariable Long roomId) {
        RoomResponseDTO room = roomService.findRoom(roomId);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping("api/chat/room")
    public ResponseEntity<Void> postRoom(@RequestBody RoomRequestDTO roomRequestDTO, Principal principal) {
        String email = principal.getName();
        roomService.addRoom(roomRequestDTO,email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
