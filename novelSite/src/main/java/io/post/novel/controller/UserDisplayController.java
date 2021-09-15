package io.post.novel.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.post.novel.auth.UserForm;
import io.post.novel.dto.UserEdit;
import io.post.novel.dto.UserRequest;
import io.post.novel.entity.SignUpUser;
import io.post.novel.service.UserDisplayService;

@Controller
public class UserDisplayController {
	
	@Autowired UserDisplayService userDisplayService;


	/*
	 * ログイン後のユーザーメニューページ表示
	 */
	@RequestMapping("/user/user_page")
	public String toUserPage(Model model, Principal principal) {
		String loginUserName = principal.getName();
		model.addAttribute("login_user",loginUserName);
		
		return "user/user_page";
	}
	
	
	/*
	 *ユーザー詳細ページ表示 
	 */
	@RequestMapping("/user/user_info/{penName}")
	public String toUserInfo(@PathVariable("penName") String penName,Model model) {
		
		SignUpUser signUpUser = userDisplayService.userInfoSearch(penName);
		model.addAttribute("user_info",signUpUser);
		
		//System.out.println(signUpUser); 出力チェック用
		
		return "user/user_info";
	}
	
	/*
	 * ユーザー情報編集ページ
	 */
	@PostMapping("/user/user_info/edit/{id}")
	public String toUserEdit(@PathVariable("id") String id, @ModelAttribute UserRequest userEdit, Model model) {
		//System.out.println(userEdit); 出力チェック用
		SignUpUser editUser = userDisplayService.userEditSearch(id);
		model.addAttribute("edit_user",editUser);
		//System.out.println(editUser);　出力チェック用
		return "user/user_info_edit";
		
	}
	
	/*
	 * ユーザーパースワード変更フォームへ移動
	 */
	@GetMapping("/user/password/edit")
	public String toPassEdit(/*@PathVariable("id") String id, Model model*/) {
		
		return "user/edit_password";
		
	}
	
	/*
	 * ユーザー情報変更
	 */
	@PostMapping("/user/user_info/edit/update")
	public String userUpdate(@ModelAttribute @Validated UserEdit userEdit, BindingResult result,  Model model,@AuthenticationPrincipal UserForm userForm) {
		
		
		 if (result.hasErrors()) {
			  
			  return "user/user_info_edit";
	        }
		
		userDisplayService.updateUserInfo(userEdit);
		userForm.setPenName(userEdit.getPenName());
		
		 SecurityContext auth = SecurityContextHolder.getContext();
		 auth.setAuthentication(new UsernamePasswordAuthenticationToken(userForm.getPenName(), userForm.getPassword(),userForm.getAuthorities()));
		
		return "redirect:/user/user_page";

	}
	
	@GetMapping("/user/user_delete")
	public String toDeleteDisplay() {
		
		return "user/delete";
	}
	
	@PostMapping("/user/user_delete/check")
	public String deleteInfoCheck(@ModelAttribute UserRequest deleteUser, Model model) {
		
		SignUpUser deleteUserInfo = userDisplayService.deleteUserSelect(deleteUser);
		model.addAttribute("delete_user", deleteUserInfo);
		
		return "user/delete_check";
	}
	
	@PostMapping("/user/user_delete/check/{id}")
	public String delete(@PathVariable("id") String id) {
		
		userDisplayService.delete(id);
		
		return "redirect:/top";
	}
 }
