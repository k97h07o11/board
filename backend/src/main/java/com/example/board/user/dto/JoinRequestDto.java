package com.example.board.user.dto;

import com.example.board.user.entity.User;
import com.example.board.validation.Password;
import com.example.board.validation.Username;
import lombok.Getter;

@Getter
public class JoinRequestDto {

    @Username
    private String username;

    @Password
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
