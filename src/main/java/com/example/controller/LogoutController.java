package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.user.model.Session;

@Controller
public class LogoutController {
	@Autowired
	Session session;
	/** ログイン画面にリダイレクト */
    @PostMapping("/logout")
    public String postLogout() {
        //セッションの無効化
        //session.setEmail(null);
        //ログイン画面へ遷移
        return "redirect:/login";
    }
}
