package com.jojoldu.com.freelecspringboot2webservice.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);  //소셜로그인 시 이미 email을 통해 생성된 사용자인지 처음 가입하는 지 판단하기 위해 
}
