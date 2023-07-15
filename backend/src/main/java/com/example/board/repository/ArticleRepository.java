package com.example.board.repository;

import com.example.board.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT a FROM Article a JOIN FETCH a.user", countQuery = "SELECT count(a) FROM Article a")
    Page<Article> findAllWithUser(Pageable pageable);

    @Query("SELECT a FROM Article a JOIN FETCH a.user WHERE a.id = :id")
    Optional<Article> findByIdWithUser(@Param("id") Long id);
}
