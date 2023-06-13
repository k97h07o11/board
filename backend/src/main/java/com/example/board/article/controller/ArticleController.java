package com.example.board.article.controller;

import com.example.board.article.entity.Article;
import com.example.board.article.service.ArticleService;
import com.example.board.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public Page<Article> getArticles(Pageable pageable) {
        Page<Article> articles = articleService.getArticleList(pageable);
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

    @PutMapping("/{articleId}")
    public void putArticle(
            @PathVariable Long articleId,
            @RequestBody Article article
    ) {
        articleService.editArticle(articleId, article);
    }

    @DeleteMapping("/{articleId}")
    public void deleteArticle(
            @PathVariable Long articleId
    ) {
        articleService.deleteArticle(articleId);
    }
}
