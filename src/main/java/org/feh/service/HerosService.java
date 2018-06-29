package org.feh.service;

import java.util.List;

import org.feh.model.Hero;
import org.feh.model.vo.HeroAllInfoVo;

public interface HerosService {
	
	public List<HeroAllInfoVo> findHerosAllInfoVos();
	
	public List<Hero> findAllHeros();
	
}
