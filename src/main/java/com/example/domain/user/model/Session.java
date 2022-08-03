package com.example.domain.user.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

//セッション定義クラス
@Component
@SessionScope
//@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
    private String password;

    //セッターとゲッターの定義
	public void setEmail(String email) {
		this.email = email;
	}
    public String getEmail() {
		return email;
	}
    public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
}