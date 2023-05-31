package com.example.board.comment.service;

import com.example.board.article.entity.Article;
import com.example.board.article.repository.ArticleRepository;
import com.example.board.comment.entity.Comment;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

    public List<Comment> getCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        return comments;
    }

    public void writeComment(Long articleId, Comment comment, User user) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(NoSuchElementException::new);
        comment.setArticle(article);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    public void editComment(Long commentId, Comment comment) {
        comment.setId(commentId);
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
