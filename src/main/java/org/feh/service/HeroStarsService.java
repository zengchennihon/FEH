package org.feh.service;

import java.util.List;

import org.feh.model.HeroStars;

public interface HeroStarsService {

	List<HeroStars> findByHeroId(Integer heroId);

	boolean saveOrUpdate(HeroStars heroStars);

	HeroStars findByHeroIdStarEquipment(Integer heroId, byte star, boolean equFlag);

}
