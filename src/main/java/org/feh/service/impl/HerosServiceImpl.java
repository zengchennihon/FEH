package org.feh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.feh.dao.HeroMapper;
import org.feh.enums.HeroCharacterEnums;
import org.feh.model.Hero;
import org.feh.model.HeroDetails;
import org.feh.model.HeroName;
import org.feh.model.HeroStars;
import org.feh.model.vo.HeroAllInfoVo;
import org.feh.model.vo.HeroBaseInfoVo;
import org.feh.service.HeroDetailsService;
import org.feh.service.HeroNameService;
import org.feh.service.HeroService;
import org.feh.service.HeroStarsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HerosServiceImpl implements HeroService {

	@Resource
	private HeroMapper heroMapper;
	@Resource
	private HeroNameService heroNameService;
	@Resource
	private HeroStarsService heroStarsService;
	@Resource
	private HeroDetailsService heroDetailsService;

	@Override
	public List<Hero> findAllHeros() {
		List<Hero> heros = heroMapper.findAll();
		return heros;
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

	@Override
	public List<HeroBaseInfoVo> findHeros() {
		List<HeroBaseInfoVo> infoVos = heroMapper.findHeros();
		return infoVos;
	}

	@Override
	public HeroAllInfoVo findAllInfoVoByAid(String aid) {
		HeroAllInfoVo allInfoVo = new HeroAllInfoVo();
		Hero hero = this.findByAid(aid);
		if(hero != null) {
			HeroName heroName = heroNameService.findByHeroId(hero.getId());
			List<HeroStars> stars = heroStarsService.findByHeroId(hero.getId());
			if(stars != null) {
				List<HeroDetails> heroDetails = heroDetailsService.findByStars(stars);
				heroDetails.forEach(d -> {
					d.setHeroCharacter(HeroCharacterEnums.findRemarkByName(d.getHeroCharacter()));
				});
				allInfoVo.setHeroDetails(heroDetails);
				allInfoVo.setHeroStars(stars);
			}
			allInfoVo.setHeroName(heroName);
			allInfoVo.setHero(hero);
		}
		return allInfoVo;
	}

}
