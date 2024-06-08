package com.jyhun.chatProject.dto;

import com.jyhun.chatProject.entity.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomResponseDTO {

    private Long id;

    private String roomName;

    private String memberName;

    public static ChatRoomResponseDTO toDTO(ChatRoom chatRoom){
        return new ChatRoomResponseDTO(chatRoom.getId(),chatRoom.getRoomName());
    }

    public ChatRoomResponseDTO(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }
}
