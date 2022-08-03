package com.example.controller;

import java.util.Date;
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
import com.example.domain.user.model.BanRegistarPastFoodsException;
import com.example.domain.user.model.Food;
import com.example.domain.user.model.Session;
import com.example.domain.user.service.FoodService;
import com.example.domain.user.service.Exception.BanRegistarPastFoodsExceptionThrowsService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/food")
@Slf4j
public class FoodRegistarController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    Session session;
    @Autowired
    BanRegistarPastFoodsExceptionThrowsService banRegistarPastFoodsExceptionThrowsService;
    
    
    /** ユーザー登録画面を表示 */
    @GetMapping("/registar")
    public String getSignup(Model model, Locale locale,
            @ModelAttribute SignupForm form) {
    	//直リンクの禁止
    	String sessioncheck=session.getEmail();
		if(sessioncheck==null) {
			//セッションにログインユーザーのセッション情報がなければエラー画面へ遷移
			return "error/accesserror";	
		}
		//modelに空のsignupFormクラスをセット
    	model.addAttribute("signupForm",form);     

        // ユーザー登録画面に遷移
        return "food/registar";
    }

    /** 食材登録処理 */
    @PostMapping("/registar")
    public String postSignup(Model model, Locale locale,
            @ModelAttribute @Validated(GroupOrder.class) SignupForm form,BindingResult bindingResult) {
    	
        // 入力チェック結果
        if (bindingResult.hasErrors()) {
            //入力エラーがあればユーザー登録画面に戻る
            return "food/registar";
        }

        // formをfoodクラスに変換
        Food food = modelMapper.map(form, Food.class);
        
        //foodにemailを設定する
        food.setEmail(session.getEmail());
        
        //登録した賞味期限が今日よりも前の場合は登録しない処理の実装
        //入力された賞味期限を取得
        Date foodLimitDay=food.getLimitday();
        
        //すでに賞味期限が切れた食材を登録しようとした場合の例外処理
        try {
			//例外が起こりうる処理
			banRegistarPastFoodsExceptionThrowsService.runSample(foodLimitDay);
			//例外が発生しなかった場合には食材登録を行う
	        foodService.foodsignup(food);
	        //ホーム画面へ遷移
			return "redirect:/food/home";
		// 例外をキャッチ			
		} catch (BanRegistarPastFoodsException e) {
			//例外が発生した場合はエラーページに遷移
			 return "/error/foodregistarerror";
			
		}
        
        
        
    }
}
