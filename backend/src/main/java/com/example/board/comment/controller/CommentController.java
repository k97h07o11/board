package com.example.board.comment.controller;

import com.example.board.comment.dto.CommentRequestDto;
import com.example.board.comment.dto.CommentResponseDto;
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
    public List<CommentResponseDto> getComments(
            @PathVariable Long articleId
    ) {
        return commentService.getCommentsByArticleId(articleId);
    }

    @PostMapping("/articles/{articleId}/comments")
    public void postComment(
            @PathVariable Long articleId,
            @RequestBody CommentRequestDto commentRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.writeComment(articleId, commentRequestDto, userDetails.getUser());
    }

    @PutMapping("/comments/{commentId}")
    public void putComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        commentService.editComment(commentId, commentRequestDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(commentId);
    }
}
