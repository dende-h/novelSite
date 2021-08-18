package io.post.novel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserDisplayController {

	@PostMapping("/user/top")
	public String toUserPage() {
	
	return "user/user_page";
	}
 }
