package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.user.model.MUser;

@Mapper
public interface UserMapper {

    /** ユーザー登録 */
    public int insertTwo(MUser muser);

    /** ユーザー取得 */
    public List<MUser> findMany();
    
    //ユーザー一件取得
    public MUser findOne(String userId);
    
    //ユーザー更新
    public void updateOne(@Param("userId") String userId,
    		@Param("userName") String userName,
    		@Param("email") String email,
    		@Param("password") String password);

    //ユーザー削除
    public int  deleteOne(@Param("userId") String userId);
    
    //ユーザー検索
    public MUser getReserchUsers(String email);
    }
