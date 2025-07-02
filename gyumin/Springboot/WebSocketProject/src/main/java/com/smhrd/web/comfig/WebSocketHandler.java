package com.smhrd.web.comfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import jakarta.websocket.server.ServerEndpoint;

// @Controller // ->  Spring Container에서 해당하는 파일을 Controller로 인식하고, 자동으로 로딩
// @ServerEndpoint("/websocket") // -> 통신의 끝 지점이기때문에, 고유의 url
public class WebSocketHandler extends TextWebSocketHandler {

	Logger logger = LoggerFactory.getLogger(getClass());
	// 소켓에 접속한 사용자들을 관리하는 자료구조
	// String -> session id값
	// WebSocketSession -> 접속한 사용자의 모든정보
	private static final HashMap<String, WebSocketSession> CLIENTS = new HashMap<>();

	// websocket에 접속되었을 때, 실행하는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// session != HttpServletSession(이거 서로 다른 영역임...!)
		// session이란?
		// -> 웹소켓에 접속한 사용자에 대한 정보를 담고있는 객체
		// -> 사용자 한명을 구분할 수 있는 id 값이 필요함!

		logger.info(("접속 성공 >>") + session.getId());
		CLIENTS.put(session.getId(), session);
	}
	// TODO Auto-generated method stub

	// websocket이 종료되었을 때, 실행하는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		CLIENTS.remove(session.getId());

	}

	// websocket에 텍스트 메세지가 전달되었을 때, 실행할 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 1. 해당하는 메세지를 보내는 사용자 정보를 구분
		String id = session.getId(); // 보내는 사람
		// 2. 보낸 사용자를 제외한 나머지 사용자들에게 메세지를 send
		CLIENTS.entrySet().forEach(data -> {
			if (data.getKey() != id) {
				// 메세지를 다른 사용자에게 보내주기
				try {
					data.getValue().sendMessage(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

	}
}
