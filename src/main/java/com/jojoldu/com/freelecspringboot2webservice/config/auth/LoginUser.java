package com.jojoldu.com.freelecspringboot2webservice.config.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  //해당 어노테이션이 메서드의 parameter에만 적용될 수 있다
@Retention(RetentionPolicy.RUNTIME)  //런타임 동안 유지
public @interface LoginUser {
}
