package org.feh.service;

import java.util.List;

import org.feh.model.Hero;
import org.feh.model.vo.HeroAllInfoVo;

public interface HeroService {
	
	public List<HeroAllInfoVo> findHerosAllInfoVos();
	
	/**
	 * 查询全部信息,根据attr进行排序
	 * @param attr
	 * @param order desc OR asc
	 * @return
	 */
	public List<HeroAllInfoVo> findHerosAllInfoVosOrderBy(String attr, String order);
	
	public List<Hero> findAllHeros();
	
	public boolean saveOrUpdate(Hero hero);
	
	public Hero findById(Integer id);

	/**
	 * aid 头像id
	 * @param aid
	 * @return
	 */
	public Hero findByAid(String aid);
	
}
