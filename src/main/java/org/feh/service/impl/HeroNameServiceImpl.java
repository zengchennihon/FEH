package org.feh.service.impl;

import javax.annotation.Resource;

import org.feh.dao.HeroNameMapper;
import org.feh.model.HeroName;
import org.feh.service.HeroNameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HeroNameServiceImpl implements HeroNameService {
	
	@Resource
	private HeroNameMapper heroNameMapper;

	@Override
	public HeroName findByHeroId(Integer heroId) {
		HeroName heroName = heroNameMapper.findByHeroId(heroId);
		return heroName;
	}

	@Override
	public boolean saveOrUpdate(HeroName heroName) {
		if(heroName != null) {
			if(heroName.getId() == null) {
				return heroNameMapper.insertSelective(heroName) > 0;
			}
			return heroNameMapper.updateByPrimaryKeySelective(heroName) > 0;
		}
		return false;
	}

}
