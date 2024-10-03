package com.jojoldu.com.freelecspringboot2webservice.config.auth.dto;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(com.jojoldu.com.freelecspringboot2webservice.domain.user.User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}