package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Session;
import com.example.form.UserSignupForm;

//管理者用ログイン機能
@RequestMapping("/user")
@Controller
public class AdminLoginController {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	Session session;
	
	/** 管理者ログイン画面を表示 */
    @GetMapping("/userlogin")
    public String getUserLogin(UserSignupForm form, Model model)  {
    	//タイムリーフに受け渡すために空のモデルを追加
    	model.addAttribute("usersignupForm",form);
    	//管理者ログイン画面遷移
    	return "user/userlogin";
    }
    
    @PostMapping("/userlogin")
    public String postUserLogin(UserSignupForm form, Model model) {
    	//ユーザーの入力値をモデルに追加
    	model.addAttribute("usersignupForm",form);
    	//入力値をMUserクラスへ変換
    	MUser user=modelMapper.map(form,MUser.class);    	
  
 
    	//管理者チェック//
    	
    	//入力された値が管理者のものと一致すればログイン成功、一致しなければエラー画面へ
    	if((user.getUserName()).equals("admin") && (user.getEmail()).equals("admin@gmail.com") && (user.getPassword()).equals("admin")) {	
    		//ログイン成功なのでセッションを設定
        	session.setEmail(user.getEmail());  
    		return "redirect:/user/userhome";
    	}
    
    	//入力値が管理者のものではない時はエラーページへ遷移
    	return  "user/user_false";
    }
    
}


