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
	private HeroMapper herosMapper;
	@Resource
	private HeroNameMapper herosNameMapper;
	@Resource
	private HeroDetailsMapper herosDetailsMapper;
	@Resource
	private HeroStarsMapper herosStarsMapper;

	@Override
	public List<Hero> findAllHeros() {
		List<Hero> heros = herosMapper.findAll();
		return heros;
	}

	@Override
	public List<HeroAllInfoVo> findHerosAllInfoVos() {
		List<HeroAllInfoVo> infoVos = herosMapper.findAllInfoVos();
		return infoVos;
	}

}
