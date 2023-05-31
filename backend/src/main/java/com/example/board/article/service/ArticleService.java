package com.example.board.article.service;

import com.example.board.article.entity.Article;
import com.example.board.article.repository.ArticleRepository;
import com.example.board.user.entity.User;
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

    public void writeArticle(Article article, User user) {
        article.setUser(user);
        articleRepository.save(article);
    }

    public void editArticle(Long articleId, Article article) {
        article.setId(articleId);
        articleRepository.save(article);
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
