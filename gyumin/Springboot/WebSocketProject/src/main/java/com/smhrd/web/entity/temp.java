package com.smhrd.web.entity;

import java.util.ArrayList;

public class temp {

	public static void main(String[] args) {
		
		
		ArrayList<String> list = new ArrayList<>();
		
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		
		// for - each가 동작하는 원리 : Iterator 객체가 돌아감
		// (1) Inteator가 list의 크기를 고정 (5)
		// (2) list.remove()
		// 		--> list안에 있는 데이터를 삭제하고, 크기를 줄이는 객체(A)가 동작
		// (3) A - Iterator 충돌! -> ConcurrentException
		// 	   -> Java는 멀티스레드 기반의 언어이기 때문이다.
		// 	   -> 한번에 여러개 일을 할 수 있는 언어이기 때문이다.
		for(String s :list) {
			System.out.println(s);
			list.remove(0);
		}
		// 해결할 수 있는 방법은 -> 순수 for문을 쓰거나...
		//				   -> 아예 다른 자료형을 쓰거나...
		//				   -> Concurrent~~~~ 자료구조가 있음.
	}

}
