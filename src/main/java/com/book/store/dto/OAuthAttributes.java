package com.book.store.dto;

import java.util.Map;

import com.book.store.user.UserData;
import com.book.store.user.UserRole;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	
	private String nameAttributeKey;
	private String name;
	private String email;
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes,String nameAttributeKey,String name,String email) {
		
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		
	}
	
	public static OAuthAttributes of(String registrationId,String userNameAttributeName,
			Map<String,Object> attributes) {
		
		
		if(registrationId.equals("naver")) {//response
			return ofNaver("id",attributes);
		}
		
		if(registrationId.equals("kakao")) {//id
			return ofKakao(userNameAttributeName,attributes);
		}
		
		
		return ofGoogle(userNameAttributeName,attributes);
				
				
				
		
		
	}
	
	private static OAuthAttributes ofGoogle(String userNameAttributeName,
			Map<String,Object> attributes) {
		
		return OAuthAttributes.builder()
				.name((String)attributes.get("name"))
				.email((String)attributes.get("email"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	
	private static OAuthAttributes ofKakao(String userNameAttributeName,
			Map<String, Object> attributes) {
		
		//kakao_account에 사용자 정보(email)가 있음
		Map<String, Object> kakaoAccount = 
				(Map<String, Object>)attributes.get("kakao_account");
		
		//kakao_profile안에 profile이라는 json객체 가 있음
		Map<String, Object> kakaoProfile = 
				(Map<String, Object>)kakaoAccount.get("profile");
		
		
		return OAuthAttributes.builder()
				.name((String)kakaoProfile.get("nickname"))
				.email((String)kakaoAccount.get("email"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();		
		
	}
	
	private static OAuthAttributes ofNaver(String userNameAttributeName,
			Map<String, Object> attributes) {
		
		Map<String, Object> response = 
				(Map<String, Object>)attributes.get("response");
		
		
		return OAuthAttributes.builder()
				.name((String)response.get("name"))
				.email((String)response.get("email"))
				.attributes(response)
				.nameAttributeKey(userNameAttributeName)
				.build();		
		
	}
	
	//db에 넣기
	public UserData toEntity() {
			
			return UserData.builder()
					.name(name)
					.email(email)
					.role(UserRole.USER.getValue())
					.build();
		}
	
	
	
}


