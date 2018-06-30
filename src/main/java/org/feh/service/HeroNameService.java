package org.feh.service;

import org.feh.model.HeroName;

public interface HeroNameService {

	HeroName findByHeroId(Integer heroId);

	boolean saveOrUpdate(HeroName heroName);

}
