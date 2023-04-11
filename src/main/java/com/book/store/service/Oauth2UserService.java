package com.book.store.service;

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

import com.book.store.dto.OAuthAttributes;
import com.book.store.user.UserData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Oauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{

	public final HttpSession httpSession;
	public final UserService userSerice;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {
	
		OAuth2UserService<OAuth2UserRequest, OAuth2User> oauthUserService = new DefaultOAuth2UserService();
		OAuth2User oauth2User = oauthUserService.loadUser(userRequest);
		
		//간편 로그인을 진행하는 회사의 코드
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		//OAuth2 로그인시 key가 되는 필드값
		//google:sub , naver:response , kakao:id
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
				.getUserNameAttributeName();
		
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oauth2User.getAttributes());
		attributes.setRegistrationId(registrationId);
		
		UserData user = null;
		try {
			user = saveOrUpdate(attributes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		httpSession.setAttribute("OauthUser", user);
		
		
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())),
				attributes.getAttributes(), attributes.getNameAttributeKey());
	}

	private UserData saveOrUpdate(OAuthAttributes attributes) throws Exception {
		
		UserData data = 
				userSerice.findUserName(attributes.getEmail())
				.map(item -> item.update(attributes.getName(),attributes.getEmail()))
				.orElse(attributes.toEntity());
		
		
		return data;
	}
	
	
	
	
}
