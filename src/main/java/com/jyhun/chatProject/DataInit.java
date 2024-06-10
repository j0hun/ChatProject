package com.jyhun.chatProject;

import com.jyhun.chatProject.constant.Role;
import com.jyhun.chatProject.entity.Room;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.RoomRepository;
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

        private final RoomRepository chatRoomRepository;
        private final MemberRepository memberRepository;
        private final PasswordEncoder passwordEncoder;

        public void dbInit() {
            Member member = new Member("admin","admin@admin.com",passwordEncoder.encode("admin@admin.com"), Role.ADMIN);
            Room chatRoom = new Room("roomName1");
//            chatRoom.changeMember(member);
            memberRepository.save(member);
            chatRoomRepository.save(chatRoom);
//            for(int i=1;i<=100;i++){
//                Member testMember = new Member("test"+i,"test"+i+"@test.com",passwordEncoder.encode("12341234"),Role.USER);
//                memberRepository.save(testMember);
//            }
        }
    }

}
