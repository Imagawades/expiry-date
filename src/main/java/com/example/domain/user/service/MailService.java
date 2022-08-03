package com.example.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.Session;


@Service
public class MailService {
	@Autowired
	Session session;
    @Autowired
    private final MailSender mailSender;
    private SimpleMailMessage msg;
    
    //コンストラクタを設定
    public MailService(MailSender mailSender) { 
        this.mailSender = mailSender;
        msg = new SimpleMailMessage();
    }
    
	public void sendMail(){
		// 送信元メールアドレスを設定
        msg.setFrom("imagawa.yuuki.0827@gmail.com");
        // 送信するタイトル 
        msg.setSubject("賞味期限の期限が迫っています");               
        //送信する本文を設定
        msg.setText(msg.getText()+"の賞味期限があと１日に迫っています"); 
        try {
            mailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
        }	
	}
	
	
    public void sendMail(String foodName,String email){
    	//食材名を設定
    	msg.setText(foodName);
    	// 送信先メールアドレスを設定
    	msg.setTo(email);
    	//同クラスのsendMailメソッドを呼び出し
    	this.sendMail();
	}
}