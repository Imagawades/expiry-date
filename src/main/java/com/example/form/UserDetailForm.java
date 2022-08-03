package com.example.form;


import lombok.Data;

//MUserクラスと同様（設計上のミス）
@Data
public class UserDetailForm{	
	private String userId;
	private String userName;
	private String email;
	private String password;
}


