package com.example.board.comment.controller;

import com.example.board.comment.dto.CommentRequestDto;
import com.example.board.comment.dto.CommentResponseDto;
import com.example.board.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @RequestBody @Valid CommentRequestDto commentRequestDto,
            Authentication authentication
    ) {
        Long userId = Long.parseLong(authentication.getName());
        commentService.writeComment(articleId, commentRequestDto, userId);
    }

    @PutMapping("/comments/{commentId}")
    public void putComment(
            @PathVariable Long commentId,
            @RequestBody @Valid CommentRequestDto commentRequestDto
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
