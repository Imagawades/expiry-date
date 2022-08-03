package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Session;
import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;

@Controller
@RequestMapping("/user")
public class UserDetailController{
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	Session session;
	
	//ユーザー詳細画面表示
	@GetMapping("/userdetail/{userId:.+}")
	public String getUser(UserDetailForm form,Model model,@PathVariable("userId") String userId) {
		
		//直リンク禁止
    	String sessioncheck=session.getEmail();
		if(sessioncheck==null) {
			//ログインユーザーのセッション情報がなければエラーページに遷移
			return "user/user_false";	
		}

		//ユーザー一件取得
		MUser user=userService.getUserOne(userId);

		//userをUserDetailFormクラスに変換
		form=modelMapper.map(user, UserDetailForm.class);
		
		//Modelに追加
		model.addAttribute("userDetailForm",form);
		
		//ユーザー詳細画面を表示
		return "/user/userdetail";
		}
	
	
	
	//ユーザー更新
	@PostMapping(value = "userdetail",params="update")
	public String updateUser(UserDetailForm form,Model model) {
		//ユーザー更新をする
		userService.updateUserOne(form.getUserId(),form.getUserName(),form.getEmail(),form.getPassword());
		//ユーザー一覧にリダイレクト
		return "redirect:/user/userlist";
		
		}
		
	
	
	//ユーザー削除
	@PostMapping(value = "userdetail",params="delete")
	public String deleteUser(UserDetailForm form,Model model) {
		//ユーザー削除
		userService.deleteUserOne(form.getUserId());
		//ユーザー一覧画面にリダイレクト
		return "redirect:/user/userlist";
		}
	

}