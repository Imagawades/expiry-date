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
@RequestMapping("/food")



public class FoodHomeController{
	@Autowired
    private FoodService foodService;
    
    @Autowired
    ModelMapper modelMapper;
    
     
    @Autowired 
    Session session;
    
	@GetMapping("/home")
	public String getHome(FoodListForm form,Model model) {
		
		//セッション情報を用いて直リンクの禁止
		String sessioncheck=session.getEmail();
		if(sessioncheck==null) {
			//セッション情報がない場合はエラーページへ遷移
			return "error/accesserror";	
		}
		//タイムリーフに渡すために空のモデルを追加
		model.addAttribute("FoodListForm",form);
		//ホーム画面へメールアドレスをhome.htmlで表示するためにセッション情報を取得
		String reserchs=session.getEmail();
		//モデルにセッション情報を追加
		model.addAttribute("reserchs",reserchs);
		
		return "food/home";
	}
	//ログインユーザー情報を登録
	@PostMapping("/home")
	public String postHome(Model model,@ModelAttribute @Validated(GroupOrder.class) FoodListForm form,BindingResult bindingResult){
		//入力チェック
		if (bindingResult.hasErrors()) {
			//入力エラーが存在した場合はlogin.htmlを再表示
			System.out.println(bindingResult.hasErrors());
			return "login/login";
		  }
		//入力されたメールアドレスとパスワードをモデルに追加
		model.addAttribute("FoodListForm",form);
		
		//モデルをMUserクラスへ変更
		MUser user=modelMapper.map(form, MUser.class);
		
		//ユーザーテーブルを検索して入力データがデータベースに存在すればreserchに代入
		List <MUser> reserch=foodService.reserchUser(user);
		
		//入力データがデータベースに存在しなかったとき
		if(reserch.size()==0){
			//UserSignupControllerに遷移
			return  "/login/login_false";
		}
		//入力データがデータベースに存在したとき
		else {
			//セッションにメールアドレスとパスワードをセット
			session.setEmail(user.getEmail());
		    session.setPassword(user.getPassword());
		    //セッション情報を取得
			String reserchs=session.getEmail();
			//セッション情報をモデルに追加
			model.addAttribute("reserchs",reserchs);	
			//home.htmlに遷移
			return  "food/home";
		}
		
	}
}