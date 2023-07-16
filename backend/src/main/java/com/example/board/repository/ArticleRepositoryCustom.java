package com.example.board.repository;

import com.example.board.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {

    Page<Article> searchAll(Pageable pageable, String keyword);
}
