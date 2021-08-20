package io.post.novel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.post.novel.dto.UserRequest;
import io.post.novel.entity.SignUpUser;

@Mapper
public interface UserSignUpMapper {

	@Insert("INSERT INTO users(pen_name,e_mail,password,birth_year,birth_month,birth_day,user_category)"
			+ "VALUES (#{penName},#{eMail},#{password},#{birthYear},#{birthMonth},#{birthDay},#{userCategory})")
	void create(UserRequest userAdd);

	@Select("SELECT * FROM users WHERE pen_name = #{penName}")
	SignUpUser userInfoSearch(UserRequest userInfo);

}
