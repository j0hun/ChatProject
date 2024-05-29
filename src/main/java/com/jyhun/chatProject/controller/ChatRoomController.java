package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.ChatRoomDTO;
import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.MemberRepository;
import com.jyhun.chatProject.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final MemberRepository memberRepository;

    @GetMapping("/chat/room")
    public String viewRoom(Model model) {
        List<ChatRoom> chatRoomList = chatRoomService.findChatRoomList();
        model.addAttribute("rooms",chatRoomList);
        return "chat/room";
    }

    @PostMapping("/chat/room")
    @ResponseBody
    public ResponseEntity<Void> postRoom(@RequestBody ChatRoomDTO chatRoomDTO, Principal principal) {
        String email = principal.getName();
        chatRoomService.addChatRoom(chatRoomDTO,email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/chat/room/{roomId}")
    public String viewChat(@PathVariable Long roomId, Model model) {
        model.addAttribute("roomId", roomId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String email = authentication.getName();
            Member member = memberRepository.findByEmail(email);
            model.addAttribute("member", member);
        }else{
            model.addAttribute("member",null);
        }
        return "chat/chat";
    }

}
