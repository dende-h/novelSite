package io.post.novel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.post.novel.dto.UserRequest;
import io.post.novel.entity.SignUpUser;
import io.post.novel.mapper.UserDisplayMapper;
import io.post.novel.mapper.UserSignUpMapper;

@Service
public class UserDisplayService {

	@Autowired UserSignUpMapper userSignUpMapper;
	@Autowired UserDisplayMapper userDisplayMapper;
	
	public SignUpUser selectName(UserRequest userName) {
		
		return userDisplayMapper.selectName(userName) ;
	}
	
	
	public SignUpUser userInfoSearch(String penName) {
		
		return userDisplayMapper.userInfoSearch(penName);
		
	}

	public SignUpUser userEditSearch(String id) {
		
		return userDisplayMapper.userEditSearch(id);
	}

	public void updateUserInfo(UserRequest userUpdate) {
		
		userDisplayMapper.updateUserInfo(userUpdate);
		
	}


	public SignUpUser deleteUserSelect(UserRequest deleteUser) {
		
		return userDisplayMapper.deleteUserSelect(deleteUser);
	}


	public void delete(String id) {
		
		userDisplayMapper.delete(id);
		
	}

	

	
}
