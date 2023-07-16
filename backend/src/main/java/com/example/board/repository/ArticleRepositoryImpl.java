package com.example.board.repository;

import com.example.board.entity.Article;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.board.entity.QArticle.article;
import static com.example.board.entity.QUser.user;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Article> searchAll(Pageable pageable, String keyword) {
        List<Article> articles = queryFactory
                .selectFrom(article)
                .join(article.user, user).fetchJoin()
                .where(titleOrContentContainsIgnoreCase(keyword))
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(article.count())
                .where(titleOrContentContainsIgnoreCase(keyword))
                .from(article);

        return PageableExecutionUtils.getPage(articles, pageable, countQuery::fetchOne);
    }

    private BooleanExpression titleOrContentContainsIgnoreCase(String keyword) {
        return StringUtils.hasText(keyword) ? article.title.containsIgnoreCase(keyword).or(article.content.containsIgnoreCase(keyword)) : null;
    }
}
