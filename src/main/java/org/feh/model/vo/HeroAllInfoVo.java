package org.feh.model.vo;

import java.util.List;

import org.feh.model.Hero;
import org.feh.model.HeroDetails;
import org.feh.model.HeroName;
import org.feh.model.HeroStars;

public class HeroAllInfoVo {

	private Hero hero;

	private HeroName heroName;

	private List<HeroDetails> heroDetails;

	private List<HeroStars> heroStars;

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

	public List<HeroDetails> getHeroDetails() {
		return heroDetails;
	}

	public void setHeroDetails(List<HeroDetails> heroDetails) {
		this.heroDetails = heroDetails;
	}

	public List<HeroStars> getHeroStars() {
		return heroStars;
	}

	public void setHeroStars(List<HeroStars> heroStars) {
		this.heroStars = heroStars;
	}

}
