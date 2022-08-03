package com.example.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.user.model.Food;
import com.example.domain.user.model.FoodAddDiff;
import com.example.domain.user.model.Session;
import com.example.domain.user.service.FoodService;
import com.example.domain.user.service.MailService;
import com.example.form.FoodListForm;
import com.example.form.SignupForm;

@Controller
@RequestMapping("/food")
public class FoodListController {

    @Autowired
    private FoodService foodService;
    
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    Session  session;   
    
    @Autowired
    MailService mailService;   

    
    /** ユーザー一覧画面を表示 */
    @GetMapping("/list")
    public String getFoodList(@ModelAttribute FoodListForm form,Model model) {
    	//直リンクの禁止
    	String sessioncheck=session.getEmail();
		if(sessioncheck==null) {
			//セッション情報がなければエラーページに遷移
			return "error/accesserror";	
		}
		
        //formをMUserクラスにする
    	model.addAttribute("form",form);
    	
    	//formをFoodクラスに変換
    	Food food=modelMapper.map(form, Food.class);
    	
    	//foodにメールアドレスをセット
    	food.setEmail(session.getEmail());
    	
    	//入力メールアドレスListを作成
        List<Food> foodList = foodService.getFoods(food);
       
        //食材の残り日数まで格納できるfoodAddDiffクラスのList型のインスタンスを作成
        List<FoodAddDiff> foodAddDiffList=new ArrayList<FoodAddDiff>();
       
        //賞味期限の計算//
        //foodListの中身をfoodAddDiffにコピー
        for(int i=0;i<foodList.size();i++) {
        	Food foods =foodList.get(i);
        	//foodAddDiffクラスのインスタンス生成
        	FoodAddDiff foodAddDiff=new FoodAddDiff();
            //その中のID,Name,Limitデータを取得しmUserAddDiffに値をセットする
        	foodAddDiff.setId(foods.getId());
        	foodAddDiff.setEmail(foods.getEmail());
            foodAddDiff.setName(foods.getName());
            foodAddDiff.setLimitday(foods.getLimitday()); 
            
            //残り日数の計算
            //カレンダークラスのインスタンスを作成
             Calendar calendar = Calendar.getInstance();
        	
        	 //現在の時刻を取得
             long now_date=calendar.getTimeInMillis();
             
             //賞味期限を取得し計算をするためにlong型に変換
             Date limit;
             limit = foods.getLimitday();        
             long limited_date = limit.getTime();
            
             //残り日数を計算
             long diff=(limited_date-now_date)/(24*60*60*1000)+1;
             
             //long型をint型に変換
             int diffday=(int)diff;
             
             //foodAddDiffに残り日数をセット
             foodAddDiff.setDiff(diffday);
             
             //foodAddDiffListにfoodAddDiffを追加
             foodAddDiffList.add(foodAddDiff);
            
        }
       
       
       // ModelにfoodAddDiffAddDiffListを登録
        model.addAttribute("foodAddDiffList",foodAddDiffList);
        
        // ユーザー一覧画面を表示
        return "food/list";
    }
    
  //ユーザー検索処理
    @PostMapping("list")
    public String postFoodList(Model model,SignupForm form,@RequestParam("number")int reserchdiff){
    	
    
    	//空のモデルを作成
    	model.addAttribute("form",form);
    
    	//formをFoodクラスに変更してモデルクラス完成（中身なし）
    	Food food=modelMapper.map(form, Food.class);
    	
    	//foodにメールアドレスを設定
    	food.setEmail(session.getEmail());
    
        //セットしたメールアドレスの食材データのみとってくる
        List<Food> foodList = foodService.getFoods(food);
    
       //foodAddDiffListインスタンスを作成
        List<FoodAddDiff> foodAddDiffList=new ArrayList<FoodAddDiff>();
    
       //食材情報をコピーし、賞味期限が指定された日数以下の食材を取得する
       foodService.copyFood(reserchdiff, foodList, foodAddDiffList);
       
       // ModelにfoodAddDiffListを登録
        model.addAttribute("foodAddDiffList",foodAddDiffList);
    	return "/food/list";
    	
   }
}
   
    
   
    
