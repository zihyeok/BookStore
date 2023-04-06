package com.book.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.book.store.user.UserData;
import com.book.store.user.UserRole;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityService implements UserDetailsService{
	
	private final UserService userService;
	private final HttpSession httpSession;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {

		
			
			Optional<UserData> searchUser = null;
			try {
				searchUser = userService.findUserName(username);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(!searchUser.isPresent()) {
				throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
			}
			
			
			UserData userData = searchUser.get();
			
			
			//권한인증을 위한 배열생성
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			
			if("admin".equals(userData.getUserRole())) {
				authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
			}else {
				authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
			}
			
			httpSession.setAttribute("user", userData);
			
			return new User(userData.getUserId(), userData.getUserPwd(), authorities);
			
		
		
	}

}
