package com.example.domain.user.model;

import lombok.Data;

//ユーザー情報を格納したモデルクラス
@Data
public class MUser {
	private String userId;
	private String userName;
    private String email;
    private String password;    
}
