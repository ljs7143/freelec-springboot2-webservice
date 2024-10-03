package com.jojoldu.com.freelecspringboot2webservice.web;


import com.jojoldu.com.freelecspringboot2webservice.config.auth.dto.SessionUser;
import com.jojoldu.com.freelecspringboot2webservice.service.posts.PostsService;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.FileStore;
//전체 흐름 : "/"에서 index페이지 반환 -> index페이지에서 글 등록 버튼 클릭 -> post/save 호출 -> postSave()를 통해 posts-save 페이지 반환
//post-save에서 등록 클릭(id=btn)-> index.js에서 id=btn을 인식하고 이를 반환 -> index.js에서 url: '/api/v1/posts' 호출 -> postSave에서 postservice의 save메서드를 통해
//saveDto를 통해 repo(Dao)를 통해 DB에 저장


@RequiredArgsConstructor
@Controller
//브라우저에서 직접 요청하는 HTTP 요청 처리
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
//        Controller에서 사용자에게 응답할 View를 생성할 때 Model을 이용하여 View에 데이터를 전달하는 방법->model.addAttribute
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("username", user.getName());
        }
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){  //경로 변수를 표시하기 위해 PathVariable 사용
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";

    }

}
