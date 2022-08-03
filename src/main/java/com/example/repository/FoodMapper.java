
package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.user.model.Food;
import com.example.domain.user.model.FoodAddDiff;
import com.example.domain.user.model.MUser;

@Mapper
public interface FoodMapper {

    /** 食材登録 */
    public int insertOne(Food food);
    
    //ユーザー検索
    public List<MUser> reserchUser(MUser user);
    
    /** 食材取得 */
    public List<Food> findMany(Food food);
    
    //食材検索
    public List<Food> findAllFoods();
    
    //食材１件取得
    public FoodAddDiff findOne(String Id);
    
    //食材削除
    public int deleteOne(@Param("Id") String Id);
}