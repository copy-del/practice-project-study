package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DataMember;
import com.example.demo.repo.DataMemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	
	@Autowired
	DataMemberRepository repository;
	@Autowired
	PasswordEncoder encoder;
	
//	@PostMapping("/login-process")
//	public String main(DataMember member, HttpSession session) {
//		// HttpSession을 사용하고 싶다면, 파라미터로 수집
//		DataMember loginMember = repository.findByIdAndPw(member.getId(),member.getPw());
//		
//		session.setAttribute("login",loginMember);
//		return "redirect:/main";
//	}
	
	@PostMapping("/join-process")
	public String joinProcess(DataMember member) {
		DataMember createMember = DataMember.createMember(member.getId(), member.getPw(),member.getRoles(),encoder);
		
		repository.save(createMember);
		return "redirect:/";
	}
	
	@GetMapping("/main")	
	// (1) 인증된 사용자의 정보를 꺼내오기 위해서 @uthenticationPrincipal User user
	// (2) request에 직접 바인딩 진행
	public String main(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("id",user.getUsername());
		model.addAttribute("roles",user.getAuthorities());
	
		
		return "main";
	}
	
	
	
	
	
}
