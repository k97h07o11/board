package com.example.board.comment.dto;

import com.example.board.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
