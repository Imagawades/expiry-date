package com.example.form;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;


//Muserクラスにバリデーションを加えたクラス
@Data
public class FoodListForm{
	private String userId;
	private String userName;
	
	@NotBlank(groups = ValidGroup1.class)
    @Email(groups = ValidGroup2.class)
	private String email;
	
	@NotBlank(groups = ValidGroup1.class)
    @Length(min = 4, max = 100, groups = ValidGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String password;
}