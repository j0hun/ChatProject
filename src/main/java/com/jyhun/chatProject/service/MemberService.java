package com.jyhun.chatProject.service;

import com.jyhun.chatProject.dto.MemberRequestDTO;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Member findMemberByEmail(String email){
        Member member = memberRepository.findByEmail(email);
        return member;
    }

    @Transactional(readOnly = true)
    public Member findMemberByName(String name){
        Member member = memberRepository.findByName(name);
        return member;
    }

    public void addMember(MemberRequestDTO memberRequestDTO) {
        Member findMember = memberRepository.findByEmail(memberRequestDTO.getEmail());
        if (findMember != null) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
        memberRequestDTO.setPassword(passwordEncoder.encode(memberRequestDTO.getPassword()));
        Member member = memberRequestDTO.toEntity();
        memberRepository.save(member);
    }

}
