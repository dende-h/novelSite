package io.post.novel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.post.novel.dto.UserRequest;
import io.post.novel.mapper.UserSignUpMapper;

@Service
public class UserSignUpService {
	
	@Autowired
	UserSignUpMapper userSignUpMapper;
	
	@Autowired
	PasswordEncoder encoder;

	public void create(UserRequest userAdd) {
		
		//パスワード暗号化
		String rawPassword = userAdd.getPassword();
		userAdd.setPassword(encoder.encode(rawPassword));
		userAdd.setRole("ROLE_admin");

		userSignUpMapper.create(userAdd);
	}

	
}
