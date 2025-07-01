package com.smhrd.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class fileController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/upload")
	public String upload() {
		return "fileupload";
	}
	
	@PostMapping("/upload")
	public String upload(MultipartFile file) throws IllegalStateException, IOException {
		logger.info("파일 상태 확인 >>"+ file.getOriginalFilename());
		// 파일이름의 중복을 제거하자
		UUID uuid = UUID.randomUUID();
		String finalFile = uuid.toString()+"-"+ file.getOriginalFilename();
		
		// 파일을 로컬에 지정한 경로에 저장하는 방법
		// 중복된 파일의 이름인 경우, 가장 마지막에 들어가는 파일만 저장
		file.transferTo(new File(finalFile));
		return "redirect:/";
	}
}
