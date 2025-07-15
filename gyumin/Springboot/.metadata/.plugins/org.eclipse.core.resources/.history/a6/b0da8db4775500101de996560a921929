package com.smhrd.web.dao;

import java.util.ArrayList;

import com.smhrd.web.entity.Board;

public class BoardDAO {

	// 게시글을 전체 조회하는 메소드
	public ArrayList<Board> selectBoard() {
		// 가변 배열과 같은 형태
		ArrayList<Board> boardList = new ArrayList<>();
		
		// 1. DB에서 데이터를 조회했다고 가정
		Board b1 = new Board(1, "게시글입니다!","주미리","게시글",0);
		boardList.add(b1);
		boardList.add(new Board(2, "게시글입니다2","신재영","게시글",1));
		boardList.add(new Board(3, "게시글입니다3","김은비","게시글",2));
		boardList.add(new Board(4, "게시글입니다4","갓재영","게시글",3));
		boardList.add(new Board(5, "게시글입니다5","갓갓영","게시글",4));
		return boardList;
		
	}
	
}
