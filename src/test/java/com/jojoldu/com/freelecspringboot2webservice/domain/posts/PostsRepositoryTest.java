package com.jojoldu.com.freelecspringboot2webservice.domain.posts;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import static com.jojoldu.com.freelecspringboot2webservice.domain.posts.Posts.*;


@RunWith(SpringRunner.class)
@SpringBootTest

public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title = "테스트 게시글";
        String content = "테스트 본문";

        System.out.println("전");
        postsRepository.save(Posts.builder().title(title).content(content).author("jojoldu@gmail.com").build());
        System.out.println("후");
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);


    }

    @Test
    public void BaseTimeEntity_등록(){
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("cotent")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        System.out.println(">>>>>>>>>>>>>>>>> createDate=" + posts.getCreatedDate()+", modifiedDate = "+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);


    }



}
