package com.jojoldu.com.freelecspringboot2webservice.web;


import com.jojoldu.com.freelecspringboot2webservice.service.posts.PostsService;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsResponseDto;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")   //postmapping은 데이터 생성 시 사용
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")  //putmapping은 데이터 update시 사용
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);

    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
