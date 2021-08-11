package io.post.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.post.novel.service.NovelService;

@Controller
public class UserSignUpController {
	
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
	
	/*
	 * @return 入力確認
	 */
	@RequestMapping(value = "/input/check", method = RequestMethod.POST)
	public String inputCheckDisplay(Model model) {
		return "input_check";
	}
	
	
	
	
	
	
}
