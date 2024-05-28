package com.jyhun.chatProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "Member_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ChatRoom_ID")
    private ChatRoom chatRoom;

    public ChatMessage(String content) {
        this.content = content;
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