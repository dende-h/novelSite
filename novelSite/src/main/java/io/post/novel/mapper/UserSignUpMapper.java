package io.post.novel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import io.post.novel.dto.UserRequest;

@Mapper
public interface UserSignUpMapper {

	@Insert("INSERT INTO users(pen_name,e_mail,password,birth_year,birth_month,birth_day,user_category)"
			+ "VALUES (#{penName},#{eMail},#{password},#{birthYear},#{birthMonth},#{birthDay},#{userCategory})")
	void create(UserRequest userAdd);

	
}
