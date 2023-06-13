package com.example.board.comment.entity;

import com.example.board.article.entity.Article;
import com.example.board.common.BaseTimeEntity;
import com.example.board.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;
}
