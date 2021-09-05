package io.post.novel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import io.post.novel.dto.UserRequest;

@Controller
public class UserLoginController {
	
	@GetMapping("/sign_in")
	public String userLogin(@ModelAttribute UserRequest userName) {
	
		return "login/login";
	}
}
