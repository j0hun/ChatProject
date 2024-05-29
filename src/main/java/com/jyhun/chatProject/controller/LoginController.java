package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.MemberRequestDTO;
import com.jyhun.chatProject.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupView(Model model) {
        model.addAttribute("member", new MemberRequestDTO());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberRequestDTO memberRequestDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "member/signup";
        }

        try {
            memberService.addMember(memberRequestDTO);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/signup";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginView(){
        return "member/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("errorMessage","아이디 또는 비밀번호를 확인해주세요.");
        return "member/login";
    }

}