package com.example.domain.user.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.Food;
import com.example.domain.user.model.FoodAddDiff;



@Service
//@RequestMapping("/login")
public class MultiThreadService extends Thread{	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	FoodService foodService;
	@Autowired
	MailService mailService;
	
	//マルチスレッド処理の実装クラス
	public void run() {
		//全件の食材情報を取得
		List<Food> foodAllList=foodService.getAllFoods();
		
		//FoodAddDiffListのインスタンスを作成
        List<FoodAddDiff> foodAddDiffList=new ArrayList<FoodAddDiff>();
        
        //foodALLListの中身をfoodAddDiffにコピー
        for(int i=0;i<foodAllList.size();i++) {
        	Food foods =foodAllList.get(i);
        	FoodAddDiff foodAddDiff=new FoodAddDiff();
        	
            //foodsクラスのID,Name,Limitデータを取得しfoodAddDiffクラスにセットする
        	foodAddDiff.setId(foods.getId());
        	foodAddDiff.setEmail(foods.getEmail());
            foodAddDiff.setName(foods.getName());
            foodAddDiff.setLimitday(foods.getLimitday()); 
                  
             //残り日数の計算をする
            //現在の時刻を取得
             Calendar calendar = Calendar.getInstance();
             long now_date=calendar.getTimeInMillis();
             
             //賞味期限を取得
             Date limit;
             limit = foods.getLimitday();        
             long limited_date = limit.getTime();
            
             //残り日数を計算する
             long diff=(limited_date-now_date)/(24*60*60*1000)+1;
             
             //long型をint型に変換
             int diffday=(int)diff;
             
             //残り日数一日以下の場合の処理
             if(diffday<=1) {
            	 //メール送信
            	 mailService.sendMail(foodAddDiff.getName(),foodAddDiff.getEmail());
             }
             //foodAddDiffに残り日数（diffday）をセット
             foodAddDiff.setDiff(diffday);
             
             //foodAddDiffListにfoodAddDiffを追加
             foodAddDiffList.add(foodAddDiff);
        }
	}
}