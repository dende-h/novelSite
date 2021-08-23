package io.post.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.post.novel.dto.UserRequest;
import io.post.novel.entity.SignUpUser;
import io.post.novel.service.UserDisplayService;

@Controller
public class UserDisplayController {
	
	@Autowired UserDisplayService userDisplayService;

	@RequestMapping("/user/user_page")
	public String toUserPage(@ModelAttribute UserRequest userName, Model model) {
		//System.out.println(userName);　出力チェック用
		//SignUpUser getUser = userDisplayService.selectName(userName);
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
	
	@PostMapping("/user/user_info/edit/{id}")
	public String toUserEdit(@PathVariable("id") String id, @ModelAttribute UserRequest userEdit, Model model) {
		//System.out.println(userEdit); 出力チェック用
		SignUpUser editUser = userDisplayService.userEditSearch(id);
		model.addAttribute("edit_user",editUser);
		//System.out.println(editUser);　出力チェック用
		return "user/user_info_edit";
		
	}
	@PostMapping("/user/user_info/edit/update")
	public String UserUpdate(@ModelAttribute @Validated UserRequest userUpdate, BindingResult result,  Model model,RedirectAttributes redirectAttributes) {
		System.out.println(userUpdate);
		
		 /*if (result.hasErrors()) {
			  
			  return "user/user_info_edit";
	        }*/
		
		userDisplayService.updateUserInfo(userUpdate);
		redirectAttributes.addFlashAttribute(userUpdate);
		
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
