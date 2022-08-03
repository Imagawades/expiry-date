package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Session;
import com.example.domain.user.service.FoodService;
import com.example.form.FoodListForm;
import com.example.form.GroupOrder;

@Controller
@RequestMapping("/user")



public class UserHomeController{
	@Autowired
    private FoodService foodService;
    
    @Autowired
    ModelMapper modelMapper;
    
     
    @Autowired 
    Session session;
    
	@GetMapping("/userhome")
	public String getUserHome(FoodListForm form,Model model) {
		
		//セッション情報を用いて直リンクの禁止
		String sessioncheck=session.getEmail();
		if(sessioncheck==null) {
			//ログインユーザーのセッション情報がなければエラー画面へ遷移
			return "user/user_false";	
		}
		//モデルに追加
		model.addAttribute("FoodListForm",form);
		
		//管理者ホーム画面へ遷移
		return "user/userhome";
	}
	//ここはそもそもログインユーザー情報を登録するコントローラー
	@PostMapping("/userhome")
	public String postHome(Model model,@ModelAttribute @Validated(GroupOrder.class) FoodListForm form,BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			//入力エラーがあればエラーページに遷移
			return "login/login";
		  }
		//入力されたメールアドレスとパスワードをモデルに追加
		model.addAttribute("FoodListForm",form);
		
		//モデルをMUserクラスへ変更
		MUser user=modelMapper.map(form, MUser.class);
		
		//ユーザーテーブルを検索して該当データが存在すればreserchに代入
		List <MUser> reserch=foodService.reserchUser(user);
		
		//該当データが存在しなかったとき
		if(reserch.size()==0){
			//UserSignupControllerに遷移
			return  "/login/login_false";
		}
		//該当データが存在したとき
		else {
			//sessionにメールアドレスとパスワードをセット
			session.setEmail(user.getEmail());
		    session.setPassword(user.getPassword());
			String reserchs=session.getEmail();
			model.addAttribute("reserchs",reserchs);	
			//管理者ホーム画面へ遷移
			return  "user/userhome";
		}
		
	}
}