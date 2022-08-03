package com.example.form;

import java.sql.Date;

import lombok.Data;

//FoodAddDiffクラスと同じ（設計上のミス）
@Data
public class FoodDetailForm{
	 private String Id;
	 private String email;
	 private String name;
	 private Date limitday;
	 private int diff;
}


