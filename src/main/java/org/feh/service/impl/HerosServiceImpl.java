package org.feh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.feh.dao.HeroDetailsMapper;
import org.feh.dao.HeroMapper;
import org.feh.dao.HeroNameMapper;
import org.feh.dao.HeroStarsMapper;
import org.feh.model.Hero;
import org.feh.model.vo.HeroAllInfoVo;
import org.feh.service.HeroService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HerosServiceImpl implements HeroService {

	@Resource
	private HeroMapper heroMapper;
	@Resource
	private HeroNameMapper heroNameMapper;
	@Resource
	private HeroDetailsMapper heroDetailsMapper;
	@Resource
	private HeroStarsMapper heroStarsMapper;

	@Override
	public List<Hero> findAllHeros() {
		List<Hero> heros = heroMapper.findAll();
		return heros;
	}

	@Override
	public List<HeroAllInfoVo> findHerosAllInfoVos() {
		List<HeroAllInfoVo> infoVos = heroMapper.findAllInfoVos();
		return infoVos;
	}

	@Override
	public List<HeroAllInfoVo> findHerosAllInfoVosOrderBy(String attr, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdate(Hero hero) {
		int i = 0;
		if(hero.getId() != null) {
			i = heroMapper.updateByPrimaryKeySelective(hero);
		} else {
			i = heroMapper.insertSelective(hero);
		}
		return i > 0;
	}

	@Override
	public Hero findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hero findByAid(String aid) {
		Hero hero = heroMapper.findByAid(aid);
		return hero;
	}

}
