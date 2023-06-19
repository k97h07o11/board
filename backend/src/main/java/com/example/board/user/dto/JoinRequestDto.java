package com.example.board.user.dto;

import com.example.board.user.entity.User;
import lombok.Getter;

@Getter
public class JoinRequestDto {

    private String username;

    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
