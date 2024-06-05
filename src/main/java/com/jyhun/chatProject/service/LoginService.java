package com.jyhun.chatProject.service;

import com.jyhun.chatProject.config.JwtProvider;
import com.jyhun.chatProject.dto.LoginDTO;
import com.jyhun.chatProject.dto.RegisterDTO;
import com.jyhun.chatProject.dto.TokenDTO;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public void register(RegisterDTO registerDTO) {
        Member findMember = memberRepository.findByEmail(registerDTO.getEmail()).orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보입니다."));
        if (findMember != null) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Member member = registerDTO.toEntity();
        memberRepository.save(member);
    }

    public TokenDTO login(LoginDTO loginDTO) {
        memberRepository.findByEmail(loginDTO.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        return new TokenDTO(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username).orElse(null);

        if (member == null) {
            throw new UsernameNotFoundException("회원을 찾을 수 없습니다.");
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();

    }
}
