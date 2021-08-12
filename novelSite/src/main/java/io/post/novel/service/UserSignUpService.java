package io.post.novel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.post.novel.dto.UserRequest;
import io.post.novel.mapper.UserSignUpMapper;

@Service
public class UserSignUpService {
	
	@Autowired
	UserSignUpMapper userSignUpMapper;

	public void create(UserRequest userAdd) {
		userSignUpMapper.create(userAdd);
		
	}

	
}
