package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
