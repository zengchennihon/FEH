package org.feh.scheduled.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.feh.model.Hero;
import org.feh.model.HeroDetails;
import org.feh.model.HeroName;
import org.feh.model.HeroStars;
import org.feh.model.functions.HeroSource;
import org.feh.model.functions.HeroSourceAttr;
import org.feh.service.HeroDetailsService;
import org.feh.service.HeroNameService;
import org.feh.service.HeroService;
import org.feh.service.HeroStarsService;
import org.feh.utils.BeanFactoryUtils;
import org.feh.utils.ResolveHerosUtils;
import org.feh.utils.UrlUtils;

public class ResolveHerosScheduledExecutor {

	private static HeroService heroService;
	private static HeroDetailsService heroDetailsService;
	private static HeroNameService heroNameService;
	private static HeroStarsService heroStarsService;
	private static Logger logger = Logger.getLogger(ResolveHerosScheduledExecutor.class);

	static {
		heroService = BeanFactoryUtils.getBean(HeroService.class);
		heroDetailsService = BeanFactoryUtils.getBean(HeroDetailsService.class);
		heroNameService = BeanFactoryUtils.getBean(HeroNameService.class);
		heroStarsService = BeanFactoryUtils.getBean(HeroStarsService.class);
	}

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

			Map<String, HeroSourceAttr> _starMap = new HashMap<>();
			_starMap.put("st3", st3);
			_starMap.put("st3_s", st3_s);
			_starMap.put("st4", st4);
			_starMap.put("st4_s", st4_s);
			_starMap.put("st5", st5);
			_starMap.put("st5_s", st5_s);

			// 获取hero头像
			String temp = UrlUtils.sendGet(ResolveHerosUtils.heroHead + aid + ".png", null, null);
			try {
				Hero hero = heroService.findByAid(aid);
				if (hero == null) {
					hero = new Hero();
					hero.setAid(aid);
				}
				hero.setHeadPortrait(temp.getBytes("UTF-8"));
				hero.setGender((byte) 1);
				boolean heroFlag = heroService.saveOrUpdate(hero);

				if (heroFlag) {
					Integer heroId = hero.getId();

					HeroName heroName = heroNameService.findByHeroId(heroId);
					if (heroName == null) {
						heroName = new HeroName();
						heroName.setHeroId(heroId);
					}
					heroName.setNameJp(name_jp);
					heroNameService.saveOrUpdate(heroName);

					List<HeroStars> heroStarsList = heroStarsService.findByHeroId(heroId);
					Map<Integer, HeroSourceAttr> _attrMap = new HashMap<>();
					if (heroStarsList == null || heroStarsList.size() > 0) {
						heroStarsList.forEach(_star -> {
							Byte star = _star.getStars();
							Boolean equipment = _star.getEquipment();
							HeroSourceAttr attr;
							if (equipment) {
								attr = _starMap.remove(String.format("st%s_s", star));
							} else {
								attr = _starMap.remove(String.format("st%s", star));
							}
							_attrMap.put(_star.getId(), attr);
						});
					}
					if (_starMap.size() > 0) {
						for (String key : _starMap.keySet()) {
							HeroSourceAttr attr = _starMap.get(key);
							
							HeroStars heroStars = new HeroStars();
							heroStars.setHeroId(heroId);
							heroStars.setStars(Byte.parseByte(key.substring(2, 3)));
							heroStars.setEquipment(key.length() > 3);
							
							boolean starsFlag = heroStarsService.saveOrUpdate(heroStars);
							if(starsFlag) {
								_attrMap.put(heroStars.getId(), attr);
							}
						}
					}
					
					if(_attrMap.size() > 0) {
						List<HeroDetails> heroDetailsList = heroDetailsService.findByStarsIds(_attrMap.keySet());
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		logger.info("*****************定时任务【" + this.getClass().toString() + "】执行完成!*****************");
	}

}
