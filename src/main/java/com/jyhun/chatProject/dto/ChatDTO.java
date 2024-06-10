package com.jyhun.chatProject.dto;

import com.jyhun.chatProject.constant.MessageType;
import com.jyhun.chatProject.entity.Chat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatDTO {

    private Long sender;
    private Long receiver;
    private String message;
    private MessageType type;

    public Chat toEntity() {
        return new Chat(message);
    }
}
