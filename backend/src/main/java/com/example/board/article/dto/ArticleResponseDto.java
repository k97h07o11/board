package com.example.board.article.dto;

import com.example.board.article.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponseDto {

    private long id;

    private String title;

    private String content;

    private Long userId;

    private String username;

    private LocalDateTime createdDate;

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
