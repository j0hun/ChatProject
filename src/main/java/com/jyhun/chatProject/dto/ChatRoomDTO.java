package com.jyhun.chatProject.dto;

import com.jyhun.chatProject.entity.ChatRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO {

    private String roomName;

    public ChatRoom toEntity(){
        return new ChatRoom(roomName);
    }

}
