package com.example.board.article.controller;

import com.example.board.article.entity.Article;
import com.example.board.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<Article> getArticles() {
        List<Article> articles = articleService.getArticleList();
        return articles;
    }

    @GetMapping("/{articleId}")
    public Article getArticle(
            @PathVariable Long articleId
    ) {
        Article article = articleService.getArticle(articleId);
        return article;
    }
}
