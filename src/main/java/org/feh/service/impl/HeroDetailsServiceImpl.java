package org.feh.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.feh.dao.HeroDetailsMapper;
import org.feh.model.HeroDetails;
import org.feh.model.HeroStars;
import org.feh.service.HeroDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HeroDetailsServiceImpl implements HeroDetailsService {
	
	@Resource
	private HeroDetailsMapper heroDetailsMapper;

	@Override
	public List<HeroDetails> findByStarsIds(List<Integer> starsIds) {
		List<HeroDetails> heroDetailsList = heroDetailsMapper.findByStarsIdsList(starsIds);
		return heroDetailsList;
	}

	@Override
	public List<HeroDetails> findByStarsIds(Set<Integer> starsIds) {
		List<HeroDetails> heroDetailsList = heroDetailsMapper.findByStarsIdsSet(starsIds);
		return heroDetailsList;
	}

	@Override
	public List<HeroDetails> findByStarsIds(Integer... starsIds) {
		List<Integer> _starsIds = Arrays.asList(starsIds);
		List<HeroDetails> heroDetailsList = heroDetailsMapper.findByStarsIdsList(_starsIds);
		return heroDetailsList;
	}

	@Override
	public List<HeroDetails> findByStars(List<HeroStars> heroStars) {
		List<Integer> starsIds = new ArrayList<>();
		heroStars.forEach(star -> {
			starsIds.add(star.getId());
		});
		return this.findByStarsIds(starsIds);
	}

	@Override
	public Integer saveOrUpdate(HeroDetails ... details) {
		List<HeroDetails> insertList = new ArrayList<>();
		List<HeroDetails> updateList = new ArrayList<>();
		for (HeroDetails hd : details) {
			if(hd.getId() == null) {
				insertList.add(hd);
			} else {
				updateList.add(hd);
			}
		}
		int num = 0;
		try {
			if(insertList.size() > 0) {
				num += heroDetailsMapper.insertByList(insertList);
			}
			if(updateList.size() > 0) {
				num += heroDetailsMapper.updateByList(updateList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public Integer saveOrUpdate(List<HeroDetails> details) {
		HeroDetails _details[] = new HeroDetails[details.size()];
		details.toArray(_details);
		if(_details.length == 0) 
			return 0;
		int num = saveOrUpdate(_details);
		return num;
	}

}
