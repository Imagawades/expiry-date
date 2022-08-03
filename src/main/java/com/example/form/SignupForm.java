package com.example.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//食材の新規登録画面でバリデーションを設定したクラス
@Data
public class SignupForm {

    @NotBlank(groups = ValidGroup1.class)
    private String name;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(groups = ValidGroup1.class)
    private Date limitday;

    

    
}