package com.example.domain.user.service;

import java.util.List;

import com.example.domain.user.model.MUser;

public interface UserService {

    /** ユーザー登録 */
    public void signup(MUser muser);

    /** ユーザー取得 */
    public List<MUser> getUsers();
    
    //ユーザー一件取得
    public MUser getUserOne(String userId);
    
    //ユーザー更新
    public void updateUserOne(String userId,String userName,String email,String password);   
    
    //ユーザー削除
    public void deleteUserOne(String userId);
    
    //ユーザー検索
    public MUser getReserchUsers(String email);
    
}
