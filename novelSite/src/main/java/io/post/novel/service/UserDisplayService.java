package io.post.novel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.post.novel.dto.UserRequest;
import io.post.novel.entity.SignUpUser;
import io.post.novel.mapper.UserSignUpMapper;

@Service
public class UserDisplayService {

	@Autowired UserSignUpMapper userSignUpMapper;
	
	public SignUpUser userInfoSearch(UserRequest userInfo) {
		
		return userSignUpMapper.userInfoSearch(userInfo);
		
	}

	
}