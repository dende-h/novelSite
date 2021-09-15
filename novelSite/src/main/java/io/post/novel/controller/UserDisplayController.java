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
		
		//ログイン情報のセッションからユーザーネームを取得
		String loginUserName = principal.getName();
		
		//画面に渡すために取得した値をmodelに詰める
		model.addAttribute("login_user",loginUserName);
		
		return "user/user_page";
	}
	
	
	/*
	 *ユーザー詳細ページ表示 
	 */
	@RequestMapping("/user/user_info/{penName}")//ユーザーネームをpathに追加
	public String toUserInfo(@PathVariable("penName") String penName,Model model) {
		
		//pathからリクエストを取得してuserInfoSearchメソッドの引数に渡す
		SignUpUser signUpUser = userDisplayService.userInfoSearch(penName);
		
		//取得したオブジェクトをmodelに詰めて画面に表示
		model.addAttribute("user_info",signUpUser);
		
		//System.out.println(signUpUser); 出力チェック用
		
		return "user/user_info";
	}
	
	
	/*
	 * ユーザー情報編集ページ
	 */
	@PostMapping("/user/user_info/edit/{id}")//ユーザーidをpathに追加
	public String toUserEdit(@PathVariable("id") String id, @ModelAttribute UserRequest userEdit, Model model) {
		
		//pathから受け取ったidを引数にして編集するレコードの取得（あらかじめフォーム内に入れておくため）
		SignUpUser editUser = userDisplayService.userEditSearch(id);
		model.addAttribute("edit_user",editUser);
		
		//System.out.println(editUser);　レコードとれてるかの出力チェック用
		return "user/user_info_edit";
		
	}
	
	
	/*
	 * ユーザー情報変更
	 */
	@PostMapping("/user/user_info/edit/update")
	public String userUpdate(@ModelAttribute @Validated UserEdit userEdit, BindingResult result,  Model model,@AuthenticationPrincipal UserForm userForm) {
		userForm.getPenName();
		
		//受けとったリクエストの入力チェック
		 if (result.hasErrors()) {
			 	model.addAttribute("edit_user", userEdit);
			  
			  return "user/user_info_edit";//エラー時は自画面遷移
	        }
		
		 //受け取ったオブジェクトを引数にDBのアップデート処理を実行
		userDisplayService.updateUserInfo(userEdit);
		
		//principalに更新したオブジェクトのユーザーネームをセットする
		userForm.setPenName(userEdit.getPenName());
		
		//setAuthenticationメソッドでUsernamePasswordAuthnticaionTolkenの中身の認証したセッション情報を更新する
		 SecurityContext auth = SecurityContextHolder.getContext();
		 auth.setAuthentication(new UsernamePasswordAuthenticationToken(userForm,userForm.getPenName(), userForm.getAuthorities()));
		
		 //ユーザーページへリダイレクト
		return "redirect:/user/user_page";
	}
	
	
	/*
	 * ユーザーパースワード変更フォームへ移動
	 */
	@GetMapping("/user/password/edit")
	public String toPassEdit() {
		
		return "user/edit_password";
	}
	
	
	/*
	 * ユーザーパスワードの入力チェック
	 */
	
	

	
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
