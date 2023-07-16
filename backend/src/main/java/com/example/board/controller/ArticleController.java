package com.example.board.controller;

import com.example.board.dto.ArticleListResponseDto;
import com.example.board.dto.ArticleRequestDto;
import com.example.board.dto.ArticleResponseDto;
import com.example.board.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Article", description = "게시글 CRUD API")
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "게시글 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ArticleListResponseDto.class))))
    })
    @GetMapping("/articles")
    public Page<ArticleListResponseDto> getArticles(
            Pageable pageable,
            @RequestParam(required = false) String keyword
    ) {
        return articleService.getArticleList(pageable, keyword);
    }

    @Operation(summary = "게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = @Content(schema = @Schema(implementation = ArticleResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "조회하려는 게시글이 존재하지 않는 경우", content = @Content)
    })
    @GetMapping("/articles/{articleId}")
    public ArticleResponseDto getArticle(
            @PathVariable Long articleId
    ) {
        return articleService.getArticle(articleId);
    }

    @Operation(summary = "게시글 작성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 작성 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "아이디가 존재하지 않는 경우", content = @Content)
    })
    @PostMapping("/articles")
    public Long postArticle(
            @RequestBody @Valid ArticleRequestDto articleRequestDto,
            Authentication authentication
    ) {
        Long userId = Long.parseLong(authentication.getName());
        return articleService.writeArticle(articleRequestDto, userId);
    }

    @Operation(summary = "게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "수졍하려는 게시글이 존재하지 않는 경우", content = @Content)
    })
    @PutMapping("/articles/{articleId}")
    public void putArticle(
            @PathVariable Long articleId,
            @RequestBody @Valid ArticleRequestDto articleRequestDto
    ) {
        articleService.editArticle(articleId, articleRequestDto);
    }

    @Operation(summary = "게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 삭제 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "삭제하려는 게시글이 존재하지 않는 경우", content = @Content)
    })
    @DeleteMapping("/articles/{articleId}")
    public void deleteArticle(
            @PathVariable Long articleId
    ) {
        articleService.deleteArticle(articleId);
    }
}
