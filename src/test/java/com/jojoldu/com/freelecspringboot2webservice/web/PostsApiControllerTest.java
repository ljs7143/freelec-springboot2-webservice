package com.jojoldu.com.freelecspringboot2webservice.web;

import com.jojoldu.com.freelecspringboot2webservice.domain.posts.Posts;
import com.jojoldu.com.freelecspringboot2webservice.domain.posts.PostsRepository;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll(); //매번 테스트 후 데이터베이스 삭제
    }

    @Test
    public void Posts_등록된다() throws Exception {
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();


        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity; // restTemplate는 통합테스트를 위해 사용되는 클래스
        responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);


    }


    @Test
    public void Posts_수정된다() throws Exception{
        //Posts 객체를 builder 패턴으로 생성하기 시작하여 build 설정된 값을 posts 객체를 생성함
        Posts savePosts = postsRepository.save(Posts.builder().title("title").content("content").author("author").build());
        Long updateId = savePosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder().title(expectedTitle).content(expectedContent).build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        //HttpEntity는 Http요청 또는 응답을 나타내는 객체로 현재의 경우
        //requestDto라는 PostsUpdateRequestDto 객체를 HTTP 요청의 본문으로 포함한 HttpEntity 객체를 생성
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when  --> restTemplate란 Spring에서 제공하는 HTTP 클라이언트로, 다양한 HTTP 메서드(GET, POST, PUT, DELETE 등)를 사용하여 원격 서버와 통신할 수 있음
        // 왼쪽은 응답, 오른쪽은 요청
        ResponseEntity<Long> responseEntity =restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }


}
