package io.post.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.post.novel.dto.UserRequest;
import io.post.novel.entity.SignUpUser;
import io.post.novel.service.UserDisplayService;

@Controller
public class UserDisplayController {
	
	@Autowired UserDisplayService userDisplayService;

	@RequestMapping("/user/user_page")
	public String toUserPage(@ModelAttribute UserRequest userName, Model model) {
		//System.out.println(userName);　出力チェック用
		
		model.addAttribute("user_name",userName);
	
		return "user/user_page";
	}
	
	@RequestMapping("/user/user_info/{penName}")
	public String toUserInfo(@PathVariable("penName") String penName,Model model) {
		
		SignUpUser signUpUser = userDisplayService.userInfoSearch(penName);
		model.addAttribute("user_info",signUpUser);
		
		//System.out.println(signUpUser); 出力チェック用
		
		return "user/user_info";
	}

 }
