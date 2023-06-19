package com.example.board.article.entity;

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
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;

    @ManyToOne
    private User user;

    private int view;

    public void edit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
