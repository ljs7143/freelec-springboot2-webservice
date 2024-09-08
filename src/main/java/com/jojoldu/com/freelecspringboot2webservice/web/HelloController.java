package com.jojoldu.com.freelecspringboot2webservice.web;


import com.jojoldu.com.freelecspringboot2webservice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return  "hello";
    }


    //RequestParam 외부에서 API로 넘긴 파라미터를 가져옴
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }

}
