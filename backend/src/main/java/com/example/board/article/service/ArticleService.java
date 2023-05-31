package com.example.board.article.service;

import com.example.board.article.entity.Article;
import com.example.board.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getArticleList() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    public Article getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(NoSuchElementException::new);
        return article;
    }
}
