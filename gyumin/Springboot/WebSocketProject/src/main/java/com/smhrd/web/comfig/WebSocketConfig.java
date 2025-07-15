package com.smhrd.web.comfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration // 환경설정 파일! Spring Container가 생성할 수 있게끔 추가
@EnableWebSocket // 웹소켓 활성화 시킬거야~
public class WebSocketConfig implements WebSocketConfigurer{
	// 우리가 직접 Container가 관리할 수 있게끔
	// WebSocketHandler를 Bean 형태로 생성해서,
	// WebSocketHandler를 관리하는 레지스트리에 추가
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		registry.addHandler(webSocketHandler(), "/websocket").setAllowedOrigins("*");
		
	}
	@Bean
	public WebSocketHandler webSocketHandler() {
		return new WebSocketHandler();
	}
	
	
	
}
