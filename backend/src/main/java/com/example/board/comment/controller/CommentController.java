package com.example.board.comment.controller;

import com.example.board.comment.entity.Comment;
import com.example.board.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/articles/{articleId}/comments")
    public List<Comment> getComments(
            @PathVariable Long articleId
    ) {
        List<Comment> comments = commentService.getCommentsByArticleId(articleId);
        return comments;
    }
}
