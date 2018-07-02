package org.feh.enums;

import org.feh.consts.HerosPropertyConsts;

public enum HeroCharacterEnums {

	INCREASE_HP_REDUCE_ATK(HerosPropertyConsts.HP, HerosPropertyConsts.ATK, "加血减攻"),
	INCREASE_HP_REDUCE_SPD(HerosPropertyConsts.HP, HerosPropertyConsts.SPD, "加血减速"),
	INCREASE_HP_REDUCE_DEF(HerosPropertyConsts.HP, HerosPropertyConsts.DEF, "加血减防"),
	INCREASE_HP_REDUCE_RES(HerosPropertyConsts.HP, HerosPropertyConsts.RES, "加血减抗"),
	
	INCREASE_ATK_REDUCE_HP(HerosPropertyConsts.ATK, HerosPropertyConsts.HP, "加攻减血"),
	INCREASE_ATK_REDUCE_SPD(HerosPropertyConsts.ATK, HerosPropertyConsts.SPD, "加攻减速"),
	INCREASE_ATK_REDUCE_DEF(HerosPropertyConsts.ATK, HerosPropertyConsts.DEF, "加攻减防"),
	INCREASE_ATK_REDUCE_RES(HerosPropertyConsts.ATK, HerosPropertyConsts.RES, "加攻减抗"),
	
	INCREASE_SPD_REDUCE_HP(HerosPropertyConsts.SPD, HerosPropertyConsts.HP, "加速减血"),
	INCREASE_SPD_REDUCE_ATK(HerosPropertyConsts.SPD, HerosPropertyConsts.ATK, "加速减攻"),
	INCREASE_SPD_REDUCE_DEF(HerosPropertyConsts.SPD, HerosPropertyConsts.DEF, "加速减防"),
	INCREASE_SPD_REDUCE_RES(HerosPropertyConsts.SPD, HerosPropertyConsts.RES, "加速减抗"),
	
	INCREASE_DEF_REDUCE_HP(HerosPropertyConsts.DEF, HerosPropertyConsts.HP, "加防减血"),
	INCREASE_DEF_REDUCE_ATK(HerosPropertyConsts.DEF, HerosPropertyConsts.ATK, "加防减攻"),
	INCREASE_DEF_REDUCE_SPD(HerosPropertyConsts.DEF, HerosPropertyConsts.SPD, "加防减速"),
	INCREASE_DEF_REDUCE_RES(HerosPropertyConsts.DEF, HerosPropertyConsts.RES, "加防减抗"),
	
	INCREASE_RES_REDUCE_HP(HerosPropertyConsts.RES, HerosPropertyConsts.HP, "加抗减血"),
	INCREASE_RES_REDUCE_ATK(HerosPropertyConsts.RES, HerosPropertyConsts.ATK, "加抗减攻"),
	INCREASE_RES_REDUCE_SPD(HerosPropertyConsts.RES, HerosPropertyConsts.SPD, "加抗减速"),
	INCREASE_RES_REDUCE_DEF(HerosPropertyConsts.RES, HerosPropertyConsts.DEF, "加抗减防"),
	
	DEFAULT_CHARACTER("", "", "标准性格");
	
	;

	private String increase;
	private String reduce;
	private String remark;
	
	public static HeroCharacterEnums findByName(String key) {
		for (HeroCharacterEnums e : HeroCharacterEnums.values()) {
			if(e.getName().equals(key)) {
				return e;
			}
		}
		return null;
	}

	HeroCharacterEnums(String increase, String reduce, String remark) {
		this.increase = increase;
		this.reduce = reduce;
		this.remark = remark;
		
	}

	public String getIncrease() {
		return increase;
	}

	public String getReduce() {
		return reduce;
	}

	public String getRemark() {
		return remark;
	}
	
	public String getName() {
		return this.name();
	}

}
