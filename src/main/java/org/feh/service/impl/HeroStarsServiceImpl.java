package org.feh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.feh.dao.HeroStarsMapper;
import org.feh.model.HeroStars;
import org.feh.service.HeroStarsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HeroStarsServiceImpl implements HeroStarsService {
	
	@Resource
	private HeroStarsMapper heroStarsMapper;

	@Override
	public List<HeroStars> findByHeroId(Integer heroId) {
		List<HeroStars> heroStars = heroStarsMapper.findByHeroId(heroId);
		return heroStars;
	}

	@Override
	public boolean saveOrUpdate(HeroStars heroStars) {
		if(heroStars != null) {
			if(heroStars.getId() != null) {
				return heroStarsMapper.updateByPrimaryKeySelective(heroStars) > 0;
			}
			return heroStarsMapper.insertSelective(heroStars) > 0;
		}
		return false;
	}

	@Override
	public HeroStars findByHeroIdStarEquipment(Integer heroId, byte star, boolean equFlag) {
		HeroStars heroStars = heroStarsMapper.findByHeroIdStarEquipment(heroId, star, equFlag);
		return heroStars;
	}

}
