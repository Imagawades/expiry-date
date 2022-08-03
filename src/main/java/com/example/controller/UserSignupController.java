package com.example.controller;

import java.util.List;
import java.util.Locale;

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

import com.example.application.service.UserApplicationService;
import com.example.domain.user.model.DuplicateException;
import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Session;
import com.example.domain.user.service.UserService;
import com.example.domain.user.service.Exception.DuplicateExceptionThrowsService;
import com.example.form.GroupOrder;
import com.example.form.UserSignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserSignupController {

    
    @Autowired
    private UserApplicationService userApplicationService;
	
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired 
    Session session;
    
    @Autowired
    DuplicateExceptionThrowsService duplicateExceptionThrowsService;
    
    /** ユーザー登録画面を表示 */
    @GetMapping("/usersignup")
    public String getUserSignup(Model model, Locale locale,
            @ModelAttribute UserSignupForm form) {
    	//モデルに追加
    	model.addAttribute("userSignForm",form);
        // ユーザー登録画面に遷移
        return "user/usersignup";
    }

    /** ユーザー登録処理 */
    @PostMapping("/usersignup")
    public String postUserSignup(Model model, Locale locale,
            @ModelAttribute @Validated(GroupOrder.class) UserSignupForm form,
            BindingResult bindingResult) {

        // 入力チェック結果
        if (bindingResult.hasErrors()) {
            //入力エラーがあった場合はユーザー登録画面に戻る
            return getUserSignup(model, locale, form);
        }

        // formをMUserクラスに変換
        MUser muser = modelMapper.map(form, MUser.class);
              
        //入力されたメールアドレスが既に登録されているかのチェック
        //全件ユーザー情報取得
        List<MUser> usersList=userService.getUsers();
         
        //ユーザーが入力したメールアドレスがすでに登録されていた場合の例外処理
        try {
			//例外が起こりうる処理
			duplicateExceptionThrowsService.runSample(muser,usersList);
			// ユーザー登録
	        userService.signup(muser);
	        //セッションの設定
	        session.setEmail(muser.getEmail());
	        String reserchs=session.getEmail();
	        model.addAttribute("reserchs",reserchs);
	        //ユーザーホーム画面へ遷移
			return "food/home";
			
		// 例外をキャッチ			
		} catch (DuplicateException e) {
			 //エラー画面へ遷移
			 return "/error/usersignuperror";
			
		}
		
              
        // ログイン画面にリダイレクト
        //return returnURL;
        
        
    }
}
