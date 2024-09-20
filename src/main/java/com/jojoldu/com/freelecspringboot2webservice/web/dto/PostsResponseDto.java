package com.jojoldu.com.freelecspringboot2webservice.web.dto;

import com.jojoldu.com.freelecspringboot2webservice.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){  //client에게 보여줄 데이터를 안전하게 은닉하는 DTO의 역할을 한다
        this.id = entity.getId();
        this.author = entity.getAuthor();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
