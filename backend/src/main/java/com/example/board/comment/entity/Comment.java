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

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void edit(String content) {
        this.content = content;
    }
}
