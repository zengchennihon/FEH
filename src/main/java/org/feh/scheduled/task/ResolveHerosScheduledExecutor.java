package org.feh.scheduled.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.feh.model.Hero;
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

			Map<String, HeroSourceAttr> _sourceMap = new HashMap<>();
			_sourceMap.put("st3", st3);
			_sourceMap.put("st3_s", st3_s);
			_sourceMap.put("st4", st4);
			_sourceMap.put("st4_s", st4_s);
			_sourceMap.put("st5", st5);
			_sourceMap.put("st5_s", st5_s);

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

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		logger.info("*****************定时任务【" + this.getClass().toString() + "】执行完成!*****************");
	}
}
