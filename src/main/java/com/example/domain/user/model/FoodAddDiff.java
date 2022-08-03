package com.example.domain.user.model;

import lombok.Data;

//食材情報に賞味期限までの残り日数を加えたモデルクラス
@Data
public class FoodAddDiff extends Food {
	 int diff;
}
