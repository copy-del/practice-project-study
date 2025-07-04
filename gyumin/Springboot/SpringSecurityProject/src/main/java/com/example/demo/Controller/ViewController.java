package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ViewController {

	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/join")
	public String join() {
		return"join";
	}
	
	@GetMapping("userPage")
	public String userPage() {
		return "userPage";
	}
	
	@GetMapping("admin")
	public String admin() {
		return "admin";
	}
}
