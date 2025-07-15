package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.DataMember;
import com.example.demo.repo.DataMemberRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {
	// DB에 있는 회원정보로 인증을 받을 수 있게끔 동작시키기 위해 필요한 파일
	
	@Autowired
	DataMemberRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		// username --> 인증받을 사용자의 정보를 building
		
		Optional<DataMember> member = repository.findById(username);
		// Optional -> DataMember 클래스를 감싸는 Wrapper Class
		// Null Pointer Exception 방지하는 기능
		
		
		DataMember userInfo = new DataMember();
		
		if(!member.isEmpty()) {
			userInfo.setId(member.get().getId());
			userInfo.setPw(member.get().getPw());
			userInfo.setRoles(member.get().getRoles());
			
		}
		
		// 클래스 가져오실 떄, package 명칭 잘 보고 가져오기!!
		return User.builder().username(userInfo.getId())
					.password(userInfo.getPw())
					.roles(userInfo.getRoles())
					.build();
		}
	
}
