package com.jojoldu.com.freelecspringboot2webservice.service.posts;
import java.util.List;
import java.util.stream.Collectors;

import com.jojoldu.com.freelecspringboot2webservice.domain.posts.Posts;
import com.jojoldu.com.freelecspringboot2webservice.domain.posts.PostsRepository;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsListResponseDto;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsResponseDto;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsUpdateRequestDto;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto)  {

        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;

    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return  new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        postsRepository.delete(posts);
    }
}
