package com.jojoldu.com.freelecspringboot2webservice.config.auth;

import com.jojoldu.com.freelecspringboot2webservice.config.auth.dto.OAuthAttributes;
import com.jojoldu.com.freelecspringboot2webservice.config.auth.dto.SessionUser;
import com.jojoldu.com.freelecspringboot2webservice.domain.user.User;
import com.jojoldu.com.freelecspringboot2webservice.domain.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //OAuth2 제공자를 통해 로그인 시, 인증된 사용자의 정보를 가져온다
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        //외부 서비스로부터 인증된 사용자 정보를 불러오는 메서드
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //어떤 제공자를 사용하여 로그인했는지 구분(Ex) google, Naver)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2 제공자가 반환하는 사용자 정보에서 사용자 이름
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        //사용자 정보를 커스텀 객체로 변환한다
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        //DB에 없으면 저자장하거나 존재하면 정보를 업데이트한다
        User user = saveOrUpdate(attributes);
        //로그인한 사용자 정보를 세션에 저장. SessionUser는 세션에 저장될 사용자 정보를 관리하는 DTO로 다음 요청부터 사용자의 로그인 상태 유지하는 데 사용
        httpSession.setAttribute("user", new SessionUser(user));
        //User의 역할을 가ㅏ져와 GrantedAuthority로 설정 -> SpringSecurity가 해당 사용자 권한 확인하고 접근 제어
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(((User) user).getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    private com.jojoldu.com.freelecspringboot2webservice.domain.user.User saveOrUpdate(OAuthAttributes attributes) {
        com.jojoldu.com.freelecspringboot2webservice.domain.user.User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
