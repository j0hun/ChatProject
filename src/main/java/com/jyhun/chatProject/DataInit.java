package com.jyhun.chatProject;

import com.jyhun.chatProject.constant.Role;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.entity.MemberRoom;
import com.jyhun.chatProject.entity.Room;
import com.jyhun.chatProject.repository.MemberRepository;
import com.jyhun.chatProject.repository.MemberRoomRepository;
import com.jyhun.chatProject.repository.RoomRepository;
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

        private final RoomRepository roomRepository;
        private final MemberRepository memberRepository;
        private final PasswordEncoder passwordEncoder;
        private final MemberRoomRepository memberRoomRepository;

        public void dbInit() {
            Member member = new Member("admin","admin@admin.com",passwordEncoder.encode("admin@admin.com"), Role.ADMIN);
            Member host = new Member("host","host@admin.com",passwordEncoder.encode("admin@admin.com"), Role.ADMIN);
            memberRepository.save(host);
            memberRepository.save(member);
            Room room = new Room("roomName1");
            roomRepository.save(room);
            MemberRoom memberRoom1 = new MemberRoom(host,room);
            MemberRoom memberRoom2 = new MemberRoom(member,room);
            memberRoomRepository.save(memberRoom1);
            memberRoomRepository.save(memberRoom2);
//            for(int i=1;i<=100;i++){
//                Member testMember = new Member("test"+i,"test"+i+"@test.com",passwordEncoder.encode("12341234"),Role.USER);
//                memberRepository.save(testMember);
//            }
        }
    }

}
