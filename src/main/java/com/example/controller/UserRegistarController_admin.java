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
import com.example.domain.user.service.Exception.DuplicateExceptionThrowsService_admin;
import com.example.form.GroupOrder;
import com.example.form.UserSignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserRegistarController_admin {

    
    @Autowired
    private UserApplicationService userApplicationService;
	
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired 
    Session session;
    
    @Autowired
    DuplicateExceptionThrowsService_admin duplicateExceptionThrowsService_admin;
    
    /** ユーザー登録画面を表示 */
    @GetMapping("/userregistar")
    public String getUserRegistar(Model model, Locale locale,
            @ModelAttribute UserSignupForm form) {
    	//モデルに追加
    	model.addAttribute("userSignForm",form);
        // 新規ユーザー登録画面に遷移
        return "user/userregistar";
    }

    /** ユーザー登録処理 */
    @PostMapping("/userregistar")
    public String postUserRegistar(Model model, Locale locale,
            @ModelAttribute @Validated(GroupOrder.class) UserSignupForm form,
            BindingResult bindingResult) {

        // 入力チェック結果
        if (bindingResult.hasErrors()) {
            //入力エラーがあればユーザー登録画面に戻る
            return getUserRegistar(model, locale, form);
        }

        // formをMUserクラスに変換
        MUser muser = modelMapper.map(form, MUser.class);
                
        //入力されたメールアドレスが既に登録されているかのチェック
        //全件ユーザー情報取得
        List<MUser> usersList=userService.getUsers();
         
        //ユーザーが登録しようとしたメールアドレスがすでに登録されたメールアドレスであった場合の例外処理
        try {
			//例外が起こりうる処理
			duplicateExceptionThrowsService_admin.runSample(muser,usersList);
			//問題なければユーザー登録
	        userService.signup(muser);
	        //セッションの設定
	        session.setEmail(muser.getEmail());
	        String reserchs=session.getEmail();
	        model.addAttribute("reserchs",reserchs);
			return "user/userhome";
			
		// 例外をキャッチ			
		} catch (DuplicateException e) {
			 //エラー画面へ遷移
			 return "/error/userregistarerror";
			
		}
       
    }
}
