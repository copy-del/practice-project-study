package com.example.demo.config;

import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration // 1) container에 적재되어야해서
@EnableWebSecurity // 2) WebSecurity가 동작할 수 있게끔 설정
// 해당하는 환경설정 파일이 web security 환경설정 파일임을 나타냄
public class SpringSecurityConfig {
	
	
	// 권한 허용 메소드 3가지
	// 1. permitAll() : 요청을 전부 허용
	// 2. hasRole(역할) : 역할에 맞는 사람만 허용
	// 3. authenticated() : 인증된 사용자만 허용
	
	
	
	
	
	// 나만의 커스텀마이징 시큐리티 필터 추가
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 예전방식
		// http.cors().disable(); --> 더 이상 권장하지 않음.
		
		// 요즘방식
		// http.cors(data -> {}) --> lambda 형식으로 사용하는 것을 권장
		// AbstractHttpConfigurer::disable
		// :: 의미하는 건 method를 reference하겠다!
		
		// 1. CORS 옵션을 비활성화 
		http.cors(AbstractHttpConfigurer::disable)
		// 2. Cross Site Request Forgery (CSRF) 옵션을 비활성화
			.csrf(AbstractHttpConfigurer::disable)
		// 3.	요청에 대한 권한 설정	
			.authorizeHttpRequests(request -> {
				// (3-1) forward를 사용하는 FC 접근허용
				request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				// (3-3) 매개변수로 들어간 url에 대해서는 접근 허용
			.requestMatchers("/join","/images/**","/join-process").permitAll()
			.requestMatchers("/admin").hasRole("admin")
			.requestMatchers("/userPage").hasAnyRole("users","admin")
			// hasRole -> 하나의 권한 | hasAnyRole -> 여러개 권한
				// (3-2) 어떤 요청이든 인증된 사용자만 접근 허용
						.anyRequest().authenticated();
			})
			// 4. 내가 원하는 인증 페이지로 넘기고 싶을 때, 설정
			.formLogin(login->{
				login.loginPage("/")
				.loginProcessingUrl("/login-process")
				// 인증받을 사용자 정보를 mapping 시키기
				.usernameParameter("id")
				.passwordParameter("pw")
				.defaultSuccessUrl("/main",true)				
				.permitAll();
			});		
		
				return http.build();
	}

	
		// DB에 있는 사용자 정보를 가져오려면 기본적으로 암호를 복호화할 수 있는 Encoder 필요함
		@Bean
		public PasswordEncoder passwordEncoder() {
			// -> 이미 잘 만들어진 BCrypt 암호화 기술을 적용하고 있는 인코딩 호출
			return new BCryptPasswordEncoder();
			// 내가 커스터마이징한 encoder를 쓰고 싶다면, 따로 파일을 작성
		}
	
	
	
	
	
}
