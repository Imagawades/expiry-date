package com.example.domain.user.service.Exception;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.domain.user.model.BanRegistarPastFoodsException;

@Service
public class BanRegistarPastFoodsExceptionThrowsService{
	//例外処理
	public void runSample(Date foodLimitDay) throws BanRegistarPastFoodsException  {
		
   	    //現在の時刻を取得
		Calendar calendar = Calendar.getInstance();
        long now_date=calendar.getTimeInMillis();
        
        //賞味期限を取得
        long limited_date =foodLimitDay.getTime();
       
        //残り日数を計算する
        long diff=(limited_date-now_date)/(24*60*60*1000)+1;
        
        //long型をint型に変換
        int diffday=(int)diff;
        //入力データが今日の日付よりも前の場合は例外処理を投げる
        if(diffday<=0) {
        	throw new BanRegistarPastFoodsException(); 
    }
		}
	}
