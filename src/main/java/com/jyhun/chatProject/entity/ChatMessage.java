package com.jyhun.chatProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String sender;

    private String content;

    @ManyToOne
    @JoinColumn(name = "ChatRoom_ID")
    private ChatRoom chatRoom;

}