package com.example.board.comment.service;

import com.example.board.article.entity.Article;
import com.example.board.article.repository.ArticleRepository;
import com.example.board.comment.dto.CommentRequestDto;
import com.example.board.comment.dto.CommentResponseDto;
import com.example.board.comment.entity.Comment;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.user.entity.User;
import com.example.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    public List<CommentResponseDto> getCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    public void writeComment(Long articleId, CommentRequestDto commentRequestDto, Long userId) {
        Comment comment = commentRequestDto.toEntity();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(NoSuchElementException::new);
        comment.setArticle(article);
        User user = userRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    public void editComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(NoSuchElementException::new);
        comment.edit(commentRequestDto.getContent());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
