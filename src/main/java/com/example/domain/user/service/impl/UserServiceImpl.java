package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    /** ユーザー登録 */
    @Override
    public void signup(MUser muser) {
       
        mapper.insertTwo(muser);
    }

    /** ユーザー取得 */
    @Override
    public List<MUser> getUsers() {
        return mapper.findMany();
    }
    
    //ユーザー一件取得
    @Override
    public MUser getUserOne(String userId) {
    	return mapper.findOne(userId);
    	}
    
    //ユーザー更新
    @Override
    public void updateUserOne(String userId,String userName,String email,String password) {
    	mapper.updateOne(userId,userName,email,password);    
    	}
    
    //ユーザー削除
    @Override
    public void deleteUserOne(String userId) {
    	int count=mapper.deleteOne(userId);    	
    	}
    
    //ユーザー検索
    public MUser getReserchUsers(String email) {
    	return mapper.getReserchUsers(email);
    } 
}
