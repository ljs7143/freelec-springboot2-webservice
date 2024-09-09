package com.jojoldu.com.freelecspringboot2webservice.service.posts;


import com.jojoldu.com.freelecspringboot2webservice.domain.posts.PostsRepository;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

}
