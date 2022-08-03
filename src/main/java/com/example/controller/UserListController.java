package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Session;
import com.example.domain.user.service.UserService;
import com.example.form.FoodListForm;
import com.example.form.UserDetailForm;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;
    
    
   @Autowired
   Session session;
   
   @Autowired
   ModelMapper modelMapper;
   /** ユーザー一覧画面を表示 */
    @GetMapping("/userlist")
    public String getUserList(Model model,@ModelAttribute UserDetailForm form) {
    	
    	//直リンク禁止
    	String sessioncheck=session.getEmail();
		if(sessioncheck==null) {
		    //ログインユーザーのセッション情報がなければエラー画面へ遷移
			return "user/user_false";	
		}
		//モデルに追加
		model.addAttribute("FoodListForm",form);
		//MUserクラスに変換
		MUser user=modelMapper.map(form, MUser.class);
		
        // ユーザー一覧取得
        List<MUser> userList = userService.getUsers();
    
        // Modelにユーザー情報登録
        model.addAttribute("userList", userList);
        // ユーザー一覧画面を表示
        return "user/userlist";
    }
    
  //ユーザー検索処理
    @PostMapping("userlist")
    public String postUserList(Model model,@ModelAttribute FoodListForm form){
    	
    	//メールアドレスが格納されたmodelを作成
    	model.addAttribute("FoodListForm",form);
    	
    	//formをFoodクラスに変更
    	MUser user=modelMapper.map(form, MUser.class);
    	
    	//ログインユーザーのセッション情報取得
    	String email=user.getEmail();
    
        //セットしたメールアドレスの食材データのみとってくる
        MUser userList = userService.getReserchUsers(email);
        //モデルに追加
        model.addAttribute("userList", userList);
        //ユーザー一覧画面へ遷移
    	return "/user/userlist";
    	
   }
}
