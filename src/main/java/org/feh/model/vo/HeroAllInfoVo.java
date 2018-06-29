package org.feh.model.vo;

import org.feh.model.Hero;
import org.feh.model.HeroDetails;
import org.feh.model.HeroName;
import org.feh.model.HeroStars;

public class HeroAllInfoVo {

	private Hero hero;

	private HeroName heroName;

	private HeroDetails heroDetails;

	private HeroStars heroStars;

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

	public HeroDetails getHeroDetails() {
		return heroDetails;
	}

	public void setHeroDetails(HeroDetails heroDetails) {
		this.heroDetails = heroDetails;
	}

	public HeroStars getHeroStars() {
		return heroStars;
	}

	public void setHeroStars(HeroStars heroStars) {
		this.heroStars = heroStars;
	}

}
