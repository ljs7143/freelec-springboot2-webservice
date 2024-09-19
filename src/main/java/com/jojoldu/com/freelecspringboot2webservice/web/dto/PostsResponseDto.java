package com.jojoldu.com.freelecspringboot2webservice.web.dto;

import com.jojoldu.com.freelecspringboot2webservice.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){

    }
}
