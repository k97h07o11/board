package com.example.board.dto;

import com.example.board.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(title = "회원가입 요청")
@Getter
public class JoinRequestDto {

    @Schema(description = "아이디")
    @NotNull
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])[a-z0-9]{6,20}")
    private String username;

    @Schema(description = "비밀번호")
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
