package com.example.domain.user.model;

//同じメールアドレスで登録されないようにするための例外処理
public class DuplicateException extends Exception{
	private static final long serialVersionUID = 1L; 
}