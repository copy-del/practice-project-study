package com.smhrd.web.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.web.entity.Board;

@Mapper
public interface BoardMapper {
	
	// 게시글을 추가하는 메소드
	public void insertBoard(Board board);
	
	// 게시글을 전체 조회하는 메소드
	public ArrayList<Board> selectBoard();
}