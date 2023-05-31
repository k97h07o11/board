package com.example.board.article.controller;

import com.example.board.article.entity.Article;
import com.example.board.article.service.ArticleService;
import com.example.board.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void postArticle(
            @RequestBody Article article,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        articleService.writeArticle(article, userDetails.getUser());
    }
}
