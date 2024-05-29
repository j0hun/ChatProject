package com.jyhun.chatProject.dto;

import com.jyhun.chatProject.constant.MessageType;
import com.jyhun.chatProject.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessageDTO {

    private Long sender;
    private Long receiver;
    private String message;
    private MessageType type;

    public ChatMessage toEntity() {
        return new ChatMessage(message);
    }
}
