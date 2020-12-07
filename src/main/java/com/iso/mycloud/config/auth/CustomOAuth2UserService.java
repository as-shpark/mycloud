package com.iso.mycloud.config.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.iso.mycloud.config.auth.vo.SessionUser;
import com.iso.mycloud.domain.user.User;
import com.iso.mycloud.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final UserRepository userRepository;
	private final HttpSession httpSession;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
		//현재 로그인 진행 중인 서비스를 구분하는 코드
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
//		System.out.println("registrationId:"+registrationId);
		
		//oauth2 로그인 진행 시 키가 되는 필드 값
		String userNameAttributeName = userRequest.getClientRegistration()
				.getProviderDetails()
				.getUserInfoEndpoint()
				.getUserNameAttributeName();
//		System.out.println("userNameAttributeName:"+userNameAttributeName);
		
		//attribute를 담을 클래스(개발자가 생성)
		OAuthAttributes attributes = OAuthAttributes
				.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
		User user = saveOrUpdate(attributes);
		httpSession.setAttribute("user", new SessionUser(user.getName(), user.getEmail(), user.getPicture()));
		
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), 
				attributes.getAttributes(), 
				attributes.getNameAttributeKey());
		
	}
	
	private User saveOrUpdate(OAuthAttributes attributes) {
		User user = userRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());
		
		return userRepository.save(user);
	}
}
