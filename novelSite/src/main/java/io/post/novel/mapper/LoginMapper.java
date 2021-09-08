package io.post.novel.mapper;

import org.apache.ibatis.annotations.Mapper;

import auth.UserForm;

@Mapper
public interface LoginMapper {

	public UserForm identifyUser(String username);

}
