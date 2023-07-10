package com.example.board.dto;

import com.example.board.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Schema(title = "댓글 작성 및 수정 요청")
@Getter
public class CommentRequestDto {

    @Schema(description = "댓글 내용")
    @NotBlank
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
