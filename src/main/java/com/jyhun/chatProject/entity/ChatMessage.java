package com.jyhun.chatProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    public ChatMessage(String message) {
        this.message = message;
    }

    public void changeMember(Member member){
        this.member = member;
        if (member != null){
            member.getChatMessageList().add(this);
        }
    }

    public void changeChatRoom(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
        if (chatRoom != null){
            chatRoom.getChatMessageList().add(this);
        }
    }

}