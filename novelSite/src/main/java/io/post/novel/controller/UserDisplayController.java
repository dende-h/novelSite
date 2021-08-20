package io.post.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.post.novel.dto.UserRequest;
import io.post.novel.entity.SignUpUser;
import io.post.novel.service.UserDisplayService;

@Controller
public class UserDisplayController {
	
	@Autowired UserDisplayService userDisplayService;

	@RequestMapping("/user/user_page")
	public String toUserPage(@ModelAttribute UserRequest userName, Model model) {
		//System.out.println(userName);
		
		model.addAttribute("user_name",userName);
	
	return "user/user_page";
	}
	
	@RequestMapping("/user/user_info")
	public String toUserInfo(@ModelAttribute UserRequest userInfo, Model model) {
		System.out.println(userInfo);
		SignUpUser signUpUser = userDisplayService.userInfoSearch(userInfo);
		model.addAttribute("user_info",signUpUser);
		System.out.println(signUpUser);
	return "user/user_info";
	}

 }
