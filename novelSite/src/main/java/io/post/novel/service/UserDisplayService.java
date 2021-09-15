package io.post.novel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.post.novel.dto.UserEdit;
import io.post.novel.dto.UserRequest;
import io.post.novel.entity.SignUpUser;
import io.post.novel.mapper.UserDisplayMapper;

@Service
public class UserDisplayService {

	@Autowired UserDisplayMapper userDisplayMapper;
	@Autowired PasswordEncoder encoder;
	
	public SignUpUser selectName(UserRequest userName) {
		
		return userDisplayMapper.selectName(userName) ;
	}
	
	
	public SignUpUser userInfoSearch(String penName) {
		
		return userDisplayMapper.userInfoSearch(penName);
		
	}

	public SignUpUser userEditSearch(String id) {
		
		return userDisplayMapper.userEditSearch(id);
	}

	public void updateUserInfo(UserEdit userEdit) {
		
		//String rawPassword = userEdit.getPassword();
		//userEdit.setPassword(encoder.encode(rawPassword));
		
		
		userDisplayMapper.updateUserInfo(userEdit);
		
	}


	public SignUpUser deleteUserSelect(UserRequest deleteUser) {
		
		return userDisplayMapper.deleteUserSelect(deleteUser);
	}


	public void delete(String id) {
		
		userDisplayMapper.delete(id);
		
	}

	
}
