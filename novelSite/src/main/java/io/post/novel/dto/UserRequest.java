package io.post.novel.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRequest implements Serializable {

	private String penName;
	private String eMail;
	private String password;
	private long birthYear;
	private long birthMonth;
	private long birthDay;
	private String userCategory;
	
	
}
