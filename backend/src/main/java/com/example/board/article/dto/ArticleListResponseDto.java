package com.example.board.article.dto;

import com.example.board.article.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleListResponseDto {

    private long id;

    private String title;

    private String username;

    private LocalDateTime createdDate;

    private int view;

    public ArticleListResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.username = article.getUser().getUsername();
        this.createdDate = article.getCreatedDate();
        this.view = article.getView();
    }
}
