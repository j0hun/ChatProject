package com.jyhun.chatProject;

import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.repository.ChatRoomRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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

        public void dbInit() {
            ChatRoom chatRoom = new ChatRoom("roomName1");
            chatRoomRepository.save(chatRoom);
        }
    }

}
