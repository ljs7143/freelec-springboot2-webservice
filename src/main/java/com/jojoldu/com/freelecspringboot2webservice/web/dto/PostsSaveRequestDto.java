package com.jojoldu.com.freelecspringboot2webservice.web.dto;


import com.jojoldu.com.freelecspringboot2webservice.domain.posts.Posts;
import com.jojoldu.com.freelecspringboot2webservice.service.posts.PostsService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private  String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .author(this.author)
                .content(this.content)
                .title(this.title)
                .build();
    }

}
