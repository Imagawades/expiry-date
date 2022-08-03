package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.user.model.Session;

//管理者ログアウト
@Controller
public class AdminLogoutController {
	@Autowired
	Session session;
	
	
    @PostMapping("/userlogout")
    public String postLogout() {
    	//セッションの無効化
    	session.setEmail(null);
    	//管理者ログイン画面へ遷移
        return "redirect:user/userlogin";
    }
}
