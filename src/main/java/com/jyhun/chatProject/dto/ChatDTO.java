package com.jyhun.chatProject.dto;

import com.jyhun.chatProject.constant.MessageType;
import com.jyhun.chatProject.entity.Chat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChatDTO {

    private Long sender;
    private Long receiver;
    private String message;
    private MessageType type;
    private LocalDateTime date;

    public Chat toEntity() {
        return new Chat(message);
    }

    public static ChatDTO toDTO(Chat chat) {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setSender(chat.getMember().getId());
        chatDTO.setReceiver(chat.getRoom().getId());
        chatDTO.setType(MessageType.TALK);
        chatDTO.setMessage(chat.getMessage());
        chatDTO.setDate(chat.getDate());
        return chatDTO;
    }
}
