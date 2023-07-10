package com.example.board.dto;

import com.example.board.entity.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(title = "게시글 목록 조회 응답")
@Getter
public class ArticleListResponseDto {

    @Schema(description = "게시글 ID")
    private long id;

    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "작성자")
    private String username;

    @Schema(description = "작성일")
    private LocalDateTime createdDate;

    @Schema(description = "조회수")
    private int view;

    public ArticleListResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.username = article.getUser().getUsername();
        this.createdDate = article.getCreatedDate();
        this.view = article.getView();
    }
}
