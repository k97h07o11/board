package com.example.board.article.repository;

import com.example.board.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a JOIN FETCH a.user WHERE a.id = :id")
    Optional<Article> findByIdWithUser(@Param("id") Long id);
}
