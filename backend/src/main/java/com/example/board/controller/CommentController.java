package com.example.board.controller;

import com.example.board.dto.CommentRequestDto;
import com.example.board.dto.CommentResponseDto;
import com.example.board.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Comment", description = "댓글 CRUD API")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 조회 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommentResponseDto.class))))
    })
    @GetMapping("/articles/{articleId}/comments")
    public List<CommentResponseDto> getComments(
            @PathVariable Long articleId
    ) {
        return commentService.getCommentsByArticleId(articleId);
    }

    @Operation(summary = "댓글 작성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 작성 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "게시글 또는 아이디가 존재하지 않는 경우", content = @Content)
    })
    @PostMapping("/articles/{articleId}/comments")
    public void postComment(
            @PathVariable Long articleId,
            @RequestBody @Valid CommentRequestDto commentRequestDto,
            Authentication authentication
    ) {
        Long userId = Long.parseLong(authentication.getName());
        commentService.writeComment(articleId, commentRequestDto, userId);
    }

    @Operation(summary = "댓글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 수정 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "수정하려는 댓글이 존재하지 않는 경우", content = @Content)
    })
    @PutMapping("/comments/{commentId}")
    public void putComment(
            @PathVariable Long commentId,
            @RequestBody @Valid CommentRequestDto commentRequestDto
    ) {
        commentService.editComment(commentId, commentRequestDto);
    }

    @Operation(summary = "댓글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 삭제 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "삭제하려는 댓글이 존재하지 않는 경우", content = @Content)
    })
    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(commentId);
    }
}
