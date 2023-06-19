package com.example.board.article.service;

import com.example.board.article.dto.ArticleListResponseDto;
import com.example.board.article.dto.ArticleRequestDto;
import com.example.board.article.dto.ArticleResponseDto;
import com.example.board.article.entity.Article;
import com.example.board.article.repository.ArticleRepository;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.user.entity.User;
import com.example.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    public Page<ArticleListResponseDto> getArticleList(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(ArticleListResponseDto::new);
    }

    public ArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(NoSuchElementException::new);
        return new ArticleResponseDto(article);
    }

    public Long writeArticle(ArticleRequestDto articleRequestDto, Long userId) {
        Article article = articleRequestDto.toEntity();
        User user = userRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);
        article.setUser(user);
        return articleRepository.save(article).getId();
    }

    public void editArticle(Long articleId, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(NoSuchElementException::new);
        article.edit(articleRequestDto.getTitle(), articleRequestDto.getContent());
        articleRepository.save(article);
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        commentRepository.deleteAllByArticleId(articleId);
        articleRepository.deleteById(articleId);
    }
}
