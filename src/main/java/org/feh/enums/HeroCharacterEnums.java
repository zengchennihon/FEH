package org.feh.enums;

import org.feh.consts.HerosPropertyConsts;

public enum HeroCharacterEnums {

	INCREASE_BLOOD_ATTACK(HerosPropertyConsts.HP, HerosPropertyConsts.ATK, "加血减攻"),
	INCREASE_BLOOD_DECELERATION(HerosPropertyConsts.HP, HerosPropertyConsts.SPD, "加血减速");
	;

	private String rise;
	private String decrease;
	private String remark;

	HeroCharacterEnums(String rise, String decrease, String remark) {
		this.rise = rise;
		this.decrease = decrease;
		this.remark = remark;
		
	}

	public String getRise() {
		return rise;
	}

	public String getDecrease() {
		return decrease;
	}

	public String getRemark() {
		return remark;
	}
	
	public String getName() {
		return this.name();
	}

}
