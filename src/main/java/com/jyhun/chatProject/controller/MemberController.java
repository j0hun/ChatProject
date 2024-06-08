package com.jyhun.chatProject.controller;

import com.jyhun.chatProject.dto.MemberResponseDTO;
import com.jyhun.chatProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("api/member/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable Long id) {
        MemberResponseDTO member = memberService.findMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

}
