package com.jojoldu.com.freelecspringboot2webservice.config.auth;

import com.jojoldu.com.freelecspringboot2webservice.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    //HttpSession을 주입받아 세션에 저장된 사용자 정보를 가져옴
    private final HttpSession httpSession;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //LoginUser annotation이 있고, 파라미터 클래스 타입이 SessionUser.class일 때 true반환
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;

    }


    @Override
    //세션에서 "user"라는 키로 저장된 객체를 가져온다. 이 객체는 일반적으로 로그인한 사용자의 정보를 담고 있는 SessionUser 인스턴스
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
