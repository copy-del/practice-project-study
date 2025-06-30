package com.smhrd.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.web.entity.Board;
import com.smhrd.web.mapper.BoardMapper;

// ~~~Application (main mathod 가지고있는 파일)
// 하위 패키지로 해당하는 파일들을 생성

// FC가 업무를 지시하는 역할을 하는 일반 자바클래스(POJO)
// --> 해당하는 파일을 통해서만. jsp or html문서를 화면에 띄울 수 있다.
@Controller
// -- > Spring Container가 해당하는 파일을 scan할 수 있게끔 해주는 annotation
// annotation은 선언구문보다 위쪽에 작성하는 것이 원칙이다.
public class BoardController {
	
	// 데이터 흐름을 체크할 수 있는 log찍는 도구를 꺼내오기
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired // -> container에 자동으로 생성된 객체를 연결하는 annotation
	BoardMapper mapper;

	// Spring boot는 RequestMapping을 선호하지 않는다!
	@GetMapping("/")
	public String board(Model model) {
		// Model -> request 경량화 버전
		// 내가 직접 생성할 필요없이, 매개변수로 받아오면 알아서 sontainer 전달

		// 2. DAO 사용
		List<Board> boardList = mapper.selectBoard();
		// 3. 결과물 받아와서, request 영역에 데이터를 바인딩
		model.addAttribute("boardList", boardList);

		return "board";
		// --> 이동해야할 페이지를 리턴
		// ViewResolver 논리적인 주소값을 물리적인 주소값으로 변환
		// 내가 설정한 prefix + board + suffix
		// * forward 방식으로 이동 : 요청 1번, 응답 1번
		// --> 초기 요청 url이 변하지 않고, 요청 객체가 다음 페이지까지 유효하다.
		// * redirect 방식 : 요청 2번, 응답 2번

	}
	
		// 글쓰기 페이지를 열어줄 수 있는 메소드
	
	
	    @GetMapping("/insert")
	    public String insert() {
	        return "insert"; // 실제로는 /WEB-INF/views/insert.jsp
	    }
	    
	    // 글쓰기 기능을 동작하게 하는 메소드
	    @PostMapping("/insert")
	    public String insert(Board board) {
	    	logger.info("데이터확인 >> " +  board);
	    	// 1. DB에 데이터를 삽입하기
	    	 mapper.insertBoard(board);
	         
	    	// 2. board.jsp로 이동(화면에 추가한 게시글이 보여야함)
	    	 return "redirect:/";
	    
	    }
	    

}
