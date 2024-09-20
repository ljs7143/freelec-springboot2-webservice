package com.jojoldu.com.freelecspringboot2webservice.domain.posts;

import com.jojoldu.com.freelecspringboot2webservice.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity    //entity 클래스에서는 절대 setter 메소드를 만들지 않는다

public class Posts extends BaseTimeEntity {
    @Id   //pk field
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String author, String content, String title){
        this.title = author;
        this.content = content;


        this.author = title;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
