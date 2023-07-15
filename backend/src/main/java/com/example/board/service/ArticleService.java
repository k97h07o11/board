package com.example.board.service;

import com.example.board.dto.ArticleListResponseDto;
import com.example.board.dto.ArticleRequestDto;
import com.example.board.dto.ArticleResponseDto;
import com.example.board.entity.Article;
import com.example.board.entity.User;
import com.example.board.repository.ArticleRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<ArticleListResponseDto> getArticleList(Pageable pageable) {
        Page<Article> articles = articleRepository.findAllWithUser(pageable);
        return articles.map(ArticleListResponseDto::new);
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepository.findByIdWithUser(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ArticleResponseDto(article);
    }

    @Transactional
    public Long writeArticle(ArticleRequestDto articleRequestDto, Long userId) {
        Article article = articleRequestDto.toEntity();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        article.setUser(user);
        return articleRepository.save(article).getId();
    }

    @Transactional
    public void editArticle(Long articleId, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        article.edit(articleRequestDto.getTitle(), articleRequestDto.getContent());
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        commentRepository.deleteAllByArticle(article);
        articleRepository.delete(article);
    }
}
