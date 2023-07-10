package com.example.board.dto;

import com.example.board.entity.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(title = "게시글 조회 응답")
@Getter
public class ArticleResponseDto {

    @Schema(description = "게시글 ID")
    private long id;

    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "게시글 내용")
    private String content;

    @Schema(description = "작성자 ID")
    private Long userId;

    @Schema(description = "작성자 닉네임")
    private String username;

    @Schema(description = "작성일")
    private LocalDateTime createdDate;

    @Schema(description = "조회수")
    private int view;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.userId = article.getUser().getId();
        this.username = article.getUser().getUsername();
        this.createdDate = article.getCreatedDate();
        this.view = article.getView();
    }
}
