package com.jojoldu.com.freelecspringboot2webservice.config;
import com.jojoldu.com.freelecspringboot2webservice.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@RequiredArgsConstructor
@Configuration
//WebMvcConfigurer인터페이스는 Spring의 MVC 설정을 커스터마이징하기 위해 사용
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver; //final 키워드가 붙어 있기에 객체 생성 시 초기화
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(loginUserArgumentResolver);
//      1. 클라이언트 요청: 사용자가 로그인 정보를 담아 POST 요청을 보냅니다.
//      2. 컨트롤러 메서드: Spring MVC는 이 요청을 처리할 login 메서드를 찾습니다. 이 메서드는 LoginUser 객체를 인자로 받을 수 있습니다.
//      3. 인자 해석기 사용: Spring은 loginUserArgumentResolver를 사용하여 요청의 로그인 정보를 해석하고, 이를 바탕으로 LoginUser 객체를 생성합니다.
//      4. 메서드 호출: 생성된 LoginUser 객체는 login 메서드의 인자로 전달되어, 메서드 내에서 사용됩니다.
    }
}