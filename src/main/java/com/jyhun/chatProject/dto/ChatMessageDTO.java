package com.jyhun.chatProject.dto;

import com.jyhun.chatProject.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {

    private String name;
    private String content;

    public ChatMessage toEntity() {
        return new ChatMessage(content);
    }
}
