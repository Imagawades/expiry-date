package com.example.domain.user.service.Exception;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.user.model.DuplicateException;
import com.example.domain.user.model.MUser;

@Service
public class DuplicateExceptionThrowsService{
	
	//@Autowired
	//DuplicateException duplicateException; 
	
	public void runSample(MUser muser, List<MUser> usersList) throws DuplicateException {
		//例外処理
		for(int i=0;i<usersList.size();i++) {
         	MUser users =usersList.get(i);
         	//すでにユーザー情報が登録されていればエラーページへ遷移
         	if(users.getEmail().equals(muser.getEmail())) {
         		
         		 System.out.println(4);
         		// throwで作成した例外を投げる
         		throw new DuplicateException(); 
         		
         	}
         	 
         }
		}
	}






