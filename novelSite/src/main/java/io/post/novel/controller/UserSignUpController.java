package io.post.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.post.novel.dto.UserRequest;
import io.post.novel.service.UserSignUpService;

@Controller
public class UserSignUpController {
	
	@Autowired
	UserSignUpService userSignUpService;
	
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
	public String inputCheckDisplay(@ModelAttribute @Validated UserRequest userRequest, Model model) {
		
		model.addAttribute("user_input", userRequest);
		
		System.out.println(userRequest.getPenName() //コンソールの出力チェック
				+ userRequest.getEMail()
				+ userRequest.getPassword()
				+ userRequest.getBirthYear() + "年"
				+ userRequest.getUserCategory());
		
		return "input_check";
	
	}
	
	@RequestMapping(value = "/signup/complete", method = RequestMethod.POST)
	public String signUp(@ModelAttribute UserRequest userAdd,Model model) {
		
		System.out.println(userAdd.getPenName() //コンソールの出力チェック
				+ userAdd.getEMail()
				+ userAdd.getPassword()
				+ userAdd.getBirthYear() + "年"
				+ userAdd.getUserCategory());
		
		userSignUpService.create(userAdd);
		//User user = userService.userDisplay(userAdd);
		//model.addAttribute("user_info", user);
		
		return "sign_up";
	}
	
	
	
	
	
}
