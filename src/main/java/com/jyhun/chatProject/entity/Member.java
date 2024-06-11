package com.jyhun.chatProject.entity;

import com.jyhun.chatProject.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Chat> chatList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberRoom> memberRoomList = new ArrayList<>();

    @Builder
    public Member(String name, String email, String password,Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
