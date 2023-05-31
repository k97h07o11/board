package com.example.board.comment.controller;

import com.example.board.comment.entity.Comment;
import com.example.board.comment.service.CommentService;
import com.example.board.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/articles/{articleId}/comments")
    public void postComment(
            @PathVariable Long articleId,
            @RequestBody Comment comment,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.writeComment(articleId, comment, userDetails.getUser());
    }

    @PutMapping("/comments/{commentId}")
    public void putComment(
            @PathVariable Long commentId,
            @RequestBody Comment comment
    ) {
        commentService.editComment(commentId, comment);
    }
}
