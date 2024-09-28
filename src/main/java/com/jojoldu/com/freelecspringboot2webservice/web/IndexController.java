package com.jojoldu.com.freelecspringboot2webservice.web;


import com.jojoldu.com.freelecspringboot2webservice.service.posts.PostsService;
import com.jojoldu.com.freelecspringboot2webservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
//        Controller에서 사용자에게 응답할 View를 생성할 때 Model을 이용하여 View에 데이터를 전달하는 방법->model.addAttribute
        model.addAttribute("posts", postsService.findAllDesc());
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
