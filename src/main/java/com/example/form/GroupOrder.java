package com.example.form;

import javax.validation.GroupSequence;

//バリデーションの優先順位を決めるクラス
@GroupSequence({ ValidGroup1.class, ValidGroup2.class })
public interface GroupOrder {

}
