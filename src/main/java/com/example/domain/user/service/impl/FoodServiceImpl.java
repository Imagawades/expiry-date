package com.example.domain.user.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.Food;
import com.example.domain.user.model.FoodAddDiff;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.FoodService;
import com.example.repository.FoodMapper;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper mapper;

    /** 食材登録 */
    @Override
    public void foodsignup(Food food) {
        mapper.insertOne(food);
    }
    
    //ユーザー検索
    @Override
    public List<MUser> reserchUser(MUser user) {
    	return mapper.reserchUser(user);
    	}
    /** 食材取得 */
    @Override
    public List<Food> getFoods(Food food) {
        return mapper.findMany(food);
    }
    
    //食材全件取得
    @Override
    public List<Food> getAllFoods(){  
    	return mapper.findAllFoods();
    	
    }
    
    //食材一件取得
    @Override
    public FoodAddDiff getFoodOne(String Id) {
    	return mapper.findOne(Id);   
    }
    
    //食材削除
    @Override
    public void deleteFoodOne(String Id) {
    	int count=mapper.deleteOne(Id);
    	}
    
    //食材コピー
    @Override
    public  List<FoodAddDiff> copyFood(int reserchdiff,List<Food> foodList, List<FoodAddDiff> foodAddDiffList) {
    	//foodListの中身をfoodAddDiffにコピー
        for(int i=0;i<foodList.size();i++) {
        	Food foods =foodList.get(i);
        	FoodAddDiff foodAddDiff=new FoodAddDiff();
            //foodsのID,Name,Limitデータを取得しfoodAddDiffに値をセットする
        	foodAddDiff.setId(foods.getId());
        	foodAddDiff.setEmail(foods.getEmail());
            foodAddDiff.setName(foods.getName());
            foodAddDiff.setLimitday(foods.getLimitday()); 
            
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
             
             //もし残り日数が指定した値以下であれば残り日数をセットして表示する
             if(diffday<=reserchdiff) {
                      //foodAddDiffに残り日数（diffs）をセット
                      foodAddDiff.setDiff(diffday);
                      
                      //foodAddDiffListにfoodAddDiffを追加
                     foodAddDiffList.add(foodAddDiff);
              }     
         }
        //foodAddDiffListを返す
        return foodAddDiffList; 
    }
}
