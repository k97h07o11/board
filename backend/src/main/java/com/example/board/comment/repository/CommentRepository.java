package com.example.board.comment.repository;

import com.example.board.article.entity.Article;
import com.example.board.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.article.id = :articleId")
    List<Comment> findAllByArticleIdWithUser(@Param("articleId") Long articleId);

    @Modifying
    @Query("DELETE FROM Comment c WHERE c.article = :article")
    void deleteAllByArticle(@Param("article") Article article);
}
