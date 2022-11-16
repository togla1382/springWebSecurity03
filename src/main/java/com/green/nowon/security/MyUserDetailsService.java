package com.green.nowon.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.entity.MemberEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	//DAO
	private final MemberEntityRepository repository;//생성자를 이용한 DI
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("로그인페이지에서 입력한 id or email:"+username);
		//mem테이블을 이용해서 인증여부 확인
		return new MyUserDetails(repository.findByEmail(username).orElseThrow());
	}

}
