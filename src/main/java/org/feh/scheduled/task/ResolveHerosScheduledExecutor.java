package org.feh.scheduled.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.feh.model.Hero;
import org.feh.model.HeroDetails;
import org.feh.model.HeroStars;
import org.feh.model.functions.HeroSource;
import org.feh.model.functions.HeroSourceAttr;
import org.feh.service.HeroDetailsService;
import org.feh.service.HeroNameService;
import org.feh.service.HeroService;
import org.feh.service.HeroStarsService;
import org.feh.utils.ResolveHerosUtils;
import org.feh.utils.UrlUtils;
import org.springframework.stereotype.Component;

@Component
public class ResolveHerosScheduledExecutor {
	
	@Resource
	private HeroService heroService;
	@Resource
	private HeroDetailsService heroDetailsService;
	@Resource
	private HeroNameService heroNameService;
	@Resource
	private HeroStarsService heroStarsService;
	
	
	public void run() {
		List<HeroSource> sources = ResolveHerosUtils.getHeroSourcesByFile();
		sources.forEach(s -> {
			String aid = s.getAid();
			String name_jp = s.getName();
			HeroSourceAttr st3 = s.getSt3();
			HeroSourceAttr st3_s = s.getSt3_s();
			HeroSourceAttr st4 = s.getSt4();
			HeroSourceAttr st4_s = s.getSt4_s();
			HeroSourceAttr st5 = s.getSt5();
			HeroSourceAttr st5_s = s.getSt5_s();
			String reco = s.getReco();
			String remark = s.getRemark();
			
			String sendGet = UrlUtils.sendGet(ResolveHerosUtils.heroHead + aid + ".png", null, null);
			try {
				Hero hero = new Hero();
				hero.setHeadPortrait(sendGet.getBytes("UTF-8"));
				hero.setGender((byte) 1);
				
				HeroStars heroStars = new HeroStars();
				heroStars.setHeroId(hero.getId());
				
				HeroDetails heroDetails = new HeroDetails();
				heroDetails.setHeroStarsId(heroStars.getId());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}

}
