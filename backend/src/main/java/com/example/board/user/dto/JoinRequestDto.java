package com.example.board.user.dto;

import com.example.board.user.entity.User;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class JoinRequestDto {

    @NotNull
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])[a-z0-9]{6,20}")
    private String username;

    @NotNull
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}")
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
