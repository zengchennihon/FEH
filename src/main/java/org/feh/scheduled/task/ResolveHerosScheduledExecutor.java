package org.feh.scheduled.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.feh.consts.HerosPropertyConsts;
import org.feh.enums.HeroCharacterEnums;
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
//		List<HeroSource> sources = ResolveHerosUtils.getHeroSourcesByFile();
		List<HeroSource> sources = ResolveHerosUtils.getHeroSourcesByUrl();
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
			String temp = UrlUtils.sendGet(ResolveHerosUtils.HEROHEAD + aid + ".png", null, null);
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
					//保存姓名信息
					this.saveOrUpdateHeroName(heroId, name_jp);
					this.saveOrUpdateHeroStars(heroId, _sourceMap);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		logger.info("*****************定时任务【" + this.getClass().toString() + "】执行完成!*****************");
	}

	private boolean saveOrUpdateHeroName(Integer heroId, String name_jp) {
		HeroName heroName = heroNameService.findByHeroId(heroId);
		if(heroName == null) {
			heroName = new HeroName();
			heroName.setHeroId(heroId);
		}
		try {
			name_jp = new String(name_jp.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		heroName.setNameJp(name_jp);
		return heroNameService.saveOrUpdate(heroName);
	}

	private void saveOrUpdateHeroStars(Integer heroId, Map<String, HeroSourceAttr> _sourceMap) {
		for (String _starStr : _sourceMap.keySet()) {
			byte star = Byte.parseByte(_starStr.substring(2, 3));
			boolean equFlag = _starStr.length() > 3;
			HeroSourceAttr heroSourceAttr = _sourceMap.get(_starStr);
			HeroStars heroStars = heroStarsService.findByHeroIdStarEquipment(heroId, star, equFlag);
			if(heroStars == null) {
				heroStars = new HeroStars();
				heroStars.setHeroId(heroId);
				heroStars.setStars(star);
				heroStars.setEquipment(equFlag);
				heroStarsService.saveOrUpdate(heroStars);
			}
			if(heroStars.getId() != null) {
				this.saveOrUpdateHeroDetails(heroStars, heroSourceAttr);
			}
		}
	}

	private void saveOrUpdateHeroDetails(HeroStars heroStars, HeroSourceAttr heroSourceAttr) {
		Integer starId = heroStars.getId();
		String[] hp = heroSourceAttr.getHP();
		
		if(hp != null) {
			List<HeroDetails> heroDetails = heroDetailsService.findByStarsIds(starId);
			List<HeroDetails> hdNeedSaveList = new ArrayList<>();
			if(heroDetails == null || heroDetails.size() == 0) {
				this.addHDNeedSaveList(heroSourceAttr, starId, hp, hdNeedSaveList, 1);
				if(hp.length > 3) {
					this.addHDNeedSaveList(heroSourceAttr, starId, hp, hdNeedSaveList, 4);
				}
				heroDetailsService.saveOrUpdate(hdNeedSaveList);
			} else {
				for (HeroDetails details : heroDetails) {
					
				}
			}
		}
		
	}

	private void addHDNeedSaveList(HeroSourceAttr heroSourceAttr, Integer starId, String [] hp, List<HeroDetails> hdNeedSaveList, Integer idx) {
		if(hp[0].matches("\\d+")) {
			//表示有性格
			HeroCharacterEnums[] characters = HeroCharacterEnums.values();
			for (HeroCharacterEnums cae : characters) {
				HeroDetails details = new HeroDetails();
				String increase = cae.getIncrease();
				String reduce = cae.getReduce();
				try {
					this.doSetProperty(details, heroSourceAttr, idx, increase, reduce);
				} catch (Exception e) {
					e.printStackTrace();
				}
				details.setHeroCharacter(cae.getName());
				hdNeedSaveList.add(details);
			}
		} else {
			//标准性格
			HeroDetails details = new HeroDetails();
			details.setHeroStarsId(starId);
			try {
				this.doSetProperty(details, heroSourceAttr, idx);
			} catch (Exception e) {
				e.printStackTrace();
			}
			details.setHeroCharacter(HeroCharacterEnums.DEFAULT_CHARACTER.getName());
			hdNeedSaveList.add(details);
		}
	}
	
	private void doSetProperty(HeroDetails details, HeroSourceAttr heroSourceAttr, Integer idx, String ... increaseOrReduce) throws Exception {
		Class<?> clazz = details.getClass();
		Map<String, Field> _tempMap = new HashMap<>();
		for (String pro : HerosPropertyConsts.fingAllConsts()) {
			Field field = clazz.getDeclaredField(pro.toLowerCase());
			_tempMap.put(pro, field);
		}
		if(increaseOrReduce != null) {
			for (int i = 0; i < increaseOrReduce.length; i++) {
				String ir = increaseOrReduce[i];
				Field field = _tempMap.remove(ir);
				Field _field = heroSourceAttr.getClass().getDeclaredField(ir);
				_field.setAccessible(true);
				field.setAccessible(true);
				String _temp[] = (String []) _field.get(heroSourceAttr);
				field.set(details, Integer.parseInt(_temp[i == 1 ? idx + 1 : idx - 1]));
				_field.setAccessible(false);
				field.setAccessible(false);
			}
		}
		for (String pro : _tempMap.keySet()) {
			Field field = _tempMap.get(pro);
			field.setAccessible(true);
			Field _field = heroSourceAttr.getClass().getDeclaredField(pro);
			_field.setAccessible(true);
			String _temp[] = (String []) _field.get(heroSourceAttr);
			field.set(details, Integer.parseInt(_temp[idx]));
			_field.setAccessible(false);
			field.setAccessible(false);
		}
	}
	
}
