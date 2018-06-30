package org.feh.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.feh.dao.HeroDetailsMapper;
import org.feh.model.HeroDetails;
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
	public Integer saveOrUpdate(HeroDetails... details) {
		List<Integer> insertList = new ArrayList<>();
		List<Integer> updateList = new ArrayList<>();
		for (HeroDetails hd : details) {
			if(hd.getId() == null) {
				insertList.add(hd.getId());
			} else {
				updateList.add(hd.getId());
			}
		}
		int num = 0;
		if(insertList.size() > 0) {
			num += heroDetailsMapper.insertByList(insertList);
		}
		if(updateList.size() > 0) {
			num += heroDetailsMapper.updateByList(updateList);
		}
		return num;
	}

	@Override
	public Integer saveOrUpdate(List<HeroDetails> details) {
		HeroDetails _details[] = new HeroDetails[details.size()];
		saveOrUpdate(details.toArray(_details));
		return null;
	}

}
