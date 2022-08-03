package com.example.domain.user.model;

import java.sql.Date;

import lombok.Data;

//食材情報を格納するモデルクラス
@Data
public class Food {
    private String Id;
    private String email;
    private String name;
    private Date limitday;
   
}
