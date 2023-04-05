package com.book.store.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.book.store.service.Oauth2UserService;
import com.book.store.user.UserRole;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final Oauth2UserService oauth2UserService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		//.anyRequest().authenticated() 상단주소외에는 전부 허용된계정만 접속
		
		http
			.authorizeHttpRequests().antMatchers("/","css/**","/images/**","/js/**").permitAll()
			.antMatchers("/api/v1/**").hasRole(UserRole.USER.name())
			
			.and()
				.csrf().disable().headers().frameOptions().disable()
			.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/")
				.deleteCookies("JESSIONID").invalidateHttpSession(true)
			.and()
				.formLogin().loginPage("/user/login").defaultSuccessUrl("/")
			.and()
				.oauth2Login().defaultSuccessUrl("/").userInfoEndpoint()
				.userService(oauth2UserService);
	
		return http.build();
	
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//AuthenticationManager는 스프링 시큐리티의 인증을 담당
	//UserSecurityService와 passwordEncoder가 자동으로 설정
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception{
		
		
		return authenticationConfiguration.getAuthenticationManager();
	}

}
