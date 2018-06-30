package org.feh.service;

import java.util.List;
import java.util.Set;

import org.feh.model.HeroDetails;

public interface HeroDetailsService {

	List<HeroDetails> findByStarsIds(List<Integer> starsIds);
	
	List<HeroDetails> findByStarsIds(Set<Integer> starsIds);
	
	List<HeroDetails> findByStarsIds(Integer ... starsIds);
	
	Integer saveOrUpdate(HeroDetails ... details);

	Integer saveOrUpdate(List<HeroDetails> details);

}
