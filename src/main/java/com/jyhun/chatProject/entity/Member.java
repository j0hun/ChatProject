package com.jyhun.chatProject.entity;

import com.jyhun.chatProject.constant.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    @Builder
    public Member(String name, String email, String password, String address, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

}
