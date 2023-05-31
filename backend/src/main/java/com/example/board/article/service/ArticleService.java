package com.example.board.article.service;

import com.example.board.article.entity.Article;
import com.example.board.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getArticleList() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }
}
