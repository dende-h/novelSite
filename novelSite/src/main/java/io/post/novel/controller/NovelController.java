package io.post.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.post.novel.service.NovelService;

@Controller
public class NovelController {
	
	@Autowired
	NovelService novelService;
	
	/*
	 * @return トップ画面
	 */
	@GetMapping(value = "/top")
	public String topDislay(){
		return "top";
	}
	
	
	/*
	 * @return サインアップフォーム
	 */
	@GetMapping(value = "/signup")
	public String signUpForm() {
		return "sign_up_form";
	}
	
	
	
	
	
	
	
	
	
}
