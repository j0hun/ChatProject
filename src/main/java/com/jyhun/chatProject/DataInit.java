package com.jyhun.chatProject;

import com.jyhun.chatProject.constant.Role;
import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.ChatRoomRepository;
import com.jyhun.chatProject.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final ChatRoomRepository chatRoomRepository;
        private final MemberRepository memberRepository;
        private final PasswordEncoder passwordEncoder;

        public void dbInit() {
            Member member = new Member("admin","admin@admin.com",passwordEncoder.encode("admin@admin.com"),"주소", Role.ADMIN);
            memberRepository.save(member);
            ChatRoom chatRoom = new ChatRoom("roomName1");
            chatRoom.changeMember(member);
            chatRoomRepository.save(chatRoom);
        }
    }

}
