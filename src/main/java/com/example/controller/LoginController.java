package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.domain.user.model.Session;
import com.example.domain.user.service.MultiThreadService;
import com.example.form.FoodListForm;

import groovyjarjarcommonscli.ParseException;

@Controller
public class LoginController {
	@Autowired
	MultiThreadService multiThreadService;
	@Autowired
	Session session;
	
	
	/** ログイン画面を表示 */
    @GetMapping("/login")
    public String getLogin(@ModelAttribute FoodListForm form, Model model) throws ParseException  {
    	model.addAttribute("foodListForm",form);
    	//セッションの初期化
    	session.setEmail(null);
    	//マルチスレッド処理を起動
    	multiThreadService.start(); 
    	//ログイン画面へ遷移
    	return "login/login";
    }
    
}
