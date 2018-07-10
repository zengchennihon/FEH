package org.feh.model.vo;

import org.feh.model.Hero;
import org.feh.model.HeroName;

public class HeroBaseInfoVo {

	private Hero hero;
	private HeroName heroName;

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public HeroName getHeroName() {
		return heroName;
	}

	public void setHeroName(HeroName heroName) {
		this.heroName = heroName;
	}

}
