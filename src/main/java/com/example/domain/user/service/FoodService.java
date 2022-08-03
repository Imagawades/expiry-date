package com.example.domain.user.service;

import java.util.List;

import com.example.domain.user.model.Food;
import com.example.domain.user.model.FoodAddDiff;
import com.example.domain.user.model.MUser;

public interface FoodService {

    /** 食材登録 */
    public void foodsignup(Food food);
    
    //ユーザ検索
    public List<MUser> reserchUser(MUser user);

    /** 食材取得 */
    public List<Food> getFoods(Food food);
    //食材全件取得
    public List<Food> getAllFoods();   
    
    //食材１件取得
    public FoodAddDiff getFoodOne(String Id);

    //食材削除
    public void deleteFoodOne(String Id);
    
    //食材データコピー
    public  List<FoodAddDiff> copyFood(int reserchdiff,  List<Food> foodList, List<FoodAddDiff> foodAddDiffList);

}
