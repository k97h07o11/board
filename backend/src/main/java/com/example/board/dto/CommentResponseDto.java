package com.example.board.dto;

import com.example.board.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(title = "댓글 조회 응답")
@Getter
public class CommentResponseDto {

    @Schema(description = "댓글 ID")
    private long id;

    @Schema(description = "댓글 내용")
    private String content;

    @Schema(description = "작성자 ID")
    private Long userId;

    @Schema(description = "작성자 닉네임")
    private String username;

    @Schema(description = "작성일")
    private LocalDateTime createdDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUser().getId();
        this.username = comment.getUser().getUsername();
        this.createdDate = comment.getCreatedDate();
    }
}
