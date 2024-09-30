package com.jojoldu.com.freelecspringboot2webservice.domain.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor  //role생성 시 key와 title을 만드시 지녀야하는 생성자를 만들어줌
public enum Role { //열거형으로 고정된 값의 집합
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}
