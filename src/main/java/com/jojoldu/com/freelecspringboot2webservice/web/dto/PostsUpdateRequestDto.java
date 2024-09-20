package com.jojoldu.com.freelecspringboot2webservice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor


public class PostsUpdateRequestDto { //update 시 데이터를 안전하게 전달해줄 dto를 생성
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;

    }
}
