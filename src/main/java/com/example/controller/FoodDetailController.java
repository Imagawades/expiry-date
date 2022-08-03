package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.FoodAddDiff;
import com.example.domain.user.model.Session;
import com.example.domain.user.service.FoodService;
import com.example.form.FoodDetailForm;

//食材詳細
@Controller
@RequestMapping("/food")
public class FoodDetailController{
	@Autowired
	private FoodService foodService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	Session session;
	
	
	//食材詳細画面を表示
	@GetMapping("/detail/{Id}")
	public String getFood(FoodDetailForm form,Model model,@PathVariable("Id") String Id){
		//セッション情報を用いて直リンクの禁止
		String sessioncheck=session.getEmail();
		if(sessioncheck==null) {
			//セッション情報がない場合はエラーページへ遷移
			return "error/accesserror";	
		}
		
		//該当食材情報を取得
		FoodAddDiff food=foodService.getFoodOne(Id);
		//FoodクラスのfoodをFoodDetailFormクラスへ変換
		form=modelMapper.map(food, FoodDetailForm.class);
		//modelに追加
		model.addAttribute("foodDetailForm",form);
		//ユーザー詳細画面を表示
		return "food/detail";
	}	
	
	//食材削除処理
	@PostMapping(value="detail",params="delete")
	public String deleteFood(FoodDetailForm form,Model model) {
		//食材の削除
		foodService.deleteFoodOne(form.getId());
		//食材一覧画面にリダイレクト
		return "redirect:/food/list";
	}
}
