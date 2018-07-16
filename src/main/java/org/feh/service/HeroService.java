package org.feh.service;

import java.util.List;

import org.feh.model.Hero;
import org.feh.model.vo.HeroAllInfoVo;
import org.feh.model.vo.HeroBaseInfoVo;

public interface HeroService {
	
	public List<Hero> findAllHeros();
	
	public boolean saveOrUpdate(Hero hero);
	
	public Hero findById(Integer id);

	/**
	 * aid 头像id
	 * @param aid
	 * @return
	 */
	public Hero findByAid(String aid);
	
	public List<HeroBaseInfoVo> findHeros();

	public HeroAllInfoVo findAllInfoVoByAid(String aid);
	
}
