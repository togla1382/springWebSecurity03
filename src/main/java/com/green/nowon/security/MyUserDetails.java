package com.green.nowon.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.green.nowon.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;


//org.springframework.security.core.userdetails.User 활용해서 만든 UserDetails 클래스
//타임리프에서 #authentication.principal.email
// sec:authentication="principal.email"
@Getter
public class MyUserDetails extends User {
	private String email;
	private String name;
	private String nickName;
	private long mno;//pk
	private boolean admin;// isAdimin()
	
	public MyUserDetails(MemberEntity entity) {
		super(entity.getEmail(), entity.getPass(), entity.getRoles().stream()
				.map(role->new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toSet())
				);
		//stream을 이용하면 안쪽 구성요소를 바꿀수 있어요 Stream<MyRole> -> Set<SimpleGrantedAuthority>
		//entity.getRoles():Set<MyRole> -> Set<SimpleGrantedAuthority>
		mno=entity.getMno();
		email=entity.getEmail();//이메일
		name=entity.getName();//이름
		nickName=entity.getNickName();//별명
		for(MyRole role:entity.getRoles()) {
			if(role.name().equals("ADMIN")) {
				admin=true;
			}
		}
	}
	
	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}

}
