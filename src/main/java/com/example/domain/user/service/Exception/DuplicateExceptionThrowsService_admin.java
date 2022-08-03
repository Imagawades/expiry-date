package com.example.domain.user.service.Exception;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.user.model.DuplicateException;
import com.example.domain.user.model.MUser;

@Service
public class DuplicateExceptionThrowsService_admin{
	
	public void runSample(MUser muser, List<MUser> usersList) throws DuplicateException {
		//メールアドレスの重複を防ぐため例外処理
		for(int i=0;i<usersList.size();i++) {
			//全ユーザー情報を取得
         	MUser users =usersList.get(i);
         	//例外が発生した場合
         	if(users.getEmail().equals(muser.getEmail())) {
         		//例外処理を投げる
         		throw new DuplicateException(); 
         	}
         }
		}
	}






