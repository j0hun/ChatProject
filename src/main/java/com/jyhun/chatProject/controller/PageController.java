package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.LoginDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("member",new LoginDTO());
        return "member/login";
    }

    @GetMapping("/chat/room")
    public String viewRoom() {
        return "chat/room";
    }

    @GetMapping("/chat/room/{roomId}")
    public String enterRoom(@PathVariable Long roomId, Model model, Authentication authentication){
        model.addAttribute("email",authentication.getName());
        model.addAttribute("roomId", roomId);
        return "chat/chat";
    }

}
