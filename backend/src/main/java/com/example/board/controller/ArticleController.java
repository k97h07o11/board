package com.example.board.controller;

import com.example.board.dto.ArticleListResponseDto;
import com.example.board.dto.ArticleRequestDto;
import com.example.board.dto.ArticleResponseDto;
import com.example.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public Page<ArticleListResponseDto> getArticles(
            Pageable pageable
    ) {
        return articleService.getArticleList(pageable);
    }

    @GetMapping("/articles/{articleId}")
    public ArticleResponseDto getArticle(
            @PathVariable Long articleId
    ) {
        return articleService.getArticle(articleId);
    }

    @PostMapping("/articles")
    public Long postArticle(
            @RequestBody @Valid ArticleRequestDto articleRequestDto,
            Authentication authentication
    ) {
        Long userId = Long.parseLong(authentication.getName());
        return articleService.writeArticle(articleRequestDto, userId);
    }

    @PutMapping("/articles/{articleId}")
    public void putArticle(
            @PathVariable Long articleId,
            @RequestBody @Valid ArticleRequestDto articleRequestDto
    ) {
        articleService.editArticle(articleId, articleRequestDto);
    }

    @DeleteMapping("/articles/{articleId}")
    public void deleteArticle(
            @PathVariable Long articleId
    ) {
        articleService.deleteArticle(articleId);
    }
}
