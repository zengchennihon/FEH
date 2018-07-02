package org.feh.scheduled.task;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
					Map<String, HeroStars> _starsMap = new HashMap<>();
					if (heroStarsList == null || heroStarsList.size() > 0) {
						heroStarsList.forEach(_star -> {
							Byte star = _star.getStars();
							Boolean equipment = _star.getEquipment();
							HeroSourceAttr attr;
							if (equipment) {
								// 不remove, 可能存在更新?
								attr = _sourceMap.get(String.format("st%s_s", star));
							} else {
								attr = _sourceMap.get(String.format("st%s", star));
							}
							_attrMap.put(_star.getId(), attr);
							_starsMap.put(aid + _star.getStars(), _star);
						});
					}
					if (_sourceMap.size() > 0) {
						for (String key : _sourceMap.keySet()) {
							HeroSourceAttr attr = _sourceMap.get(key);
							String _star = key.substring(2, 3);
							if(attr.getHP() == null)
								break;

							HeroStars heroStars = _starsMap.get(aid + _star);
							if (heroStars == null) {
								heroStars = new HeroStars();
								heroStars.setHeroId(heroId);
								heroStars.setStars(Byte.parseByte(_star));
								// key = st3_s length大于3表示装备
								heroStars.setEquipment(key.length() > 3);
								heroStars.setLevel(1);
							}
							boolean starsFlag = heroStarsService.saveOrUpdate(heroStars);
							if (starsFlag) {
								_attrMap.put(heroStars.getId(), attr);
								_starsMap.put(aid + heroStars.getStars(), heroStars);
							}
							if(attr.getHP().length > 3) {
								heroStars.setId(null);
								heroStars.setLevel(40);
								starsFlag = heroStarsService.saveOrUpdate(heroStars);
								if (starsFlag) {
									_attrMap.put(heroStars.getId(), attr);
									_starsMap.put(aid + heroStars.getStars(), heroStars);
								}
							}
						}
					}

					if (_attrMap.size() > 0) {
						List<HeroDetails> needSaveOrUpdateList = new ArrayList<>();
						
						for (String aid_starId : _starsMap.keySet()) {
							HeroStars heroStars = _starsMap.get(aid_starId);
							// 判断是什么性格
							Integer starId = heroStars.getId();
							HeroSourceAttr sourceAttr = _attrMap.get(starId);
							// sourceAttr 按性格分类
							String[] hp = sourceAttr.getHP();
							String[] atk = sourceAttr.getATK();
							String[] spd = sourceAttr.getSPD();
							String[] def = sourceAttr.getDEF();
							String[] res = sourceAttr.getRES();
							Map<String, Integer> attrMap = this.getAttrByCharacter(hp, atk, spd, def, res);
							
							List<HeroDetails> heroDetailsList = heroDetailsService.findByStarsIds(starId);
							List<HeroCharacterEnums> characterEnumsList = Arrays.asList(HeroCharacterEnums.values());
							
							if (heroDetailsList.size() > 0) {
								heroDetailsList.forEach(hd -> {
									for (String key : attrMap.keySet()) {
										String[] _temp = key.split("-");
										Integer attrNum = attrMap.remove(key);
										
										if(_temp[0].equals(hd.getHeroCharacter()) && heroStars.getLevel() == Integer.parseInt(_temp[1])) {
											HeroCharacterEnums _enum = HeroCharacterEnums.findByName(hd.getHeroCharacter());
											characterEnumsList.remove(_enum);
											Class<? extends HeroDetails> clazz = hd.getClass();
											Field _field;
											try {
												_field = clazz.getDeclaredField(_temp[2].toLowerCase());
												_field.setAccessible(true);
												_field.set(hd, attrNum);
												_field.setAccessible(false);
											} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
												e.printStackTrace();
											}
										}
									}
									needSaveOrUpdateList.add(hd);
								});
							}
							characterEnumsList.forEach(_enum -> {
								String _name = _enum.getName();
								HeroDetails hd = new HeroDetails();
								hd.setHeroStarsId(starId);
								hd.setHeroCharacter(_name);
								setNeedSaveOrUpdateList(needSaveOrUpdateList, attrMap, _name, hd, heroStars.getLevel());
							});

							heroDetailsService.saveOrUpdate(needSaveOrUpdateList);
						}

					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		logger.info("*****************定时任务【" + this.getClass().toString() + "】执行完成!*****************");
	}

	private void setNeedSaveOrUpdateList(List<HeroDetails> needSaveOrUpdateList, Map<String, Integer> attrMap,
			String _name, HeroDetails hd, int lv) {
		boolean flag = true;
		for (String con : HerosPropertyConsts.fingAllConsts()) {
			Integer attrNum = attrMap.get(_name + "-" + lv + "-" + con);
			if(attrNum == null) {
				flag = false;
				break;
			}
			Class<? extends HeroDetails> clazz = hd.getClass();
			Field _field;
			try {
				_field = clazz.getDeclaredField(con.toLowerCase());
				_field.setAccessible(true);
				_field.set(hd, attrNum);
				_field.setAccessible(false);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (flag) {
			needSaveOrUpdateList.add(hd);
		}
	}

	private Map<String, Integer> getAttrByCharacter(String[] hp, String[] atk, String[] spd, String[] def,
			String[] res) {
		Map<String, Integer> _tempMap = new HashMap<>();
		HeroCharacterEnums[] characterEnums = HeroCharacterEnums.values();
		for (int i = 0; i < characterEnums.length; i++) {
			HeroCharacterEnums _ca = characterEnums[i];
			String increase = _ca.getIncrease();
			String reduce = _ca.getReduce();
			try {
				if (_ca.getName().equals(HeroCharacterEnums.DEFAULT_CHARACTER.getName())) {
					setCharacterMap(_tempMap, HeroCharacterEnums.DEFAULT_CHARACTER.getName(), 1,
							Integer.parseInt(hp[1]), Integer.parseInt(atk[1]), Integer.parseInt(spd[1]),
							Integer.parseInt(def[1]), Integer.parseInt(res[1]));
				} else {
					set__tempCharacter(1, hp, atk, spd, def, res, _tempMap, _ca, increase, reduce);
					if (hp.length > 3) {
						set__tempCharacter(4, hp, atk, spd, def, res, _tempMap, _ca, increase, reduce);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return _tempMap;
	}

	private void set__tempCharacter(int idx, String[] hp, String[] atk, String[] spd, String[] def, String[] res,
			Map<String, Integer> _tempMap, HeroCharacterEnums _ca, String increase, String reduce) {
		Map<String, Integer> __temp = new HashMap<>();
		__temp.put(HerosPropertyConsts.HP, Integer.parseInt(hp[idx]));
		__temp.put(HerosPropertyConsts.ATK, Integer.parseInt(atk[idx]));
		__temp.put(HerosPropertyConsts.SPD, Integer.parseInt(spd[idx]));
		__temp.put(HerosPropertyConsts.DEF, Integer.parseInt(def[idx]));
		__temp.put(HerosPropertyConsts.RES, Integer.parseInt(res[idx]));

		getAttrbute(__temp, increase, idx + 1, hp, atk, spd, def, res);
		getAttrbute(__temp, reduce, idx - 1, hp, atk, spd, def, res);
		
		int lv = 1;
		if(idx == 4)
			lv = 40;
		setCharacterMap(_tempMap, _ca.getName(), lv, __temp.get(HerosPropertyConsts.HP),
				__temp.get(HerosPropertyConsts.ATK), __temp.get(HerosPropertyConsts.SPD),
				__temp.get(HerosPropertyConsts.DEF), __temp.get(HerosPropertyConsts.RES));
	}

	private void getAttrbute(Map<String, Integer> __temp, String increaseOrReduce, int idx, String[] hp, String[] atk,
			String[] spd, String[] def, String[] res) {
		switch (increaseOrReduce) {
		case HerosPropertyConsts.HP:
			__temp.put(HerosPropertyConsts.HP, Integer.parseInt(hp[idx]));
			break;
		case HerosPropertyConsts.ATK:
			__temp.put(HerosPropertyConsts.ATK, Integer.parseInt(atk[idx]));
			break;
		case HerosPropertyConsts.SPD:
			__temp.put(HerosPropertyConsts.SPD, Integer.parseInt(spd[idx]));
			break;
		case HerosPropertyConsts.DEF:
			__temp.put(HerosPropertyConsts.DEF, Integer.parseInt(def[idx]));
			break;
		case HerosPropertyConsts.RES:
			__temp.put(HerosPropertyConsts.RES, Integer.parseInt(res[idx]));
			break;
		default:
			break;
		}
	}

	private void setCharacterMap(Map<String, Integer> _tempMap, String character, int lv, Integer... attr) {
		_tempMap.put(character + "-" + lv + "-" + HerosPropertyConsts.HP, attr[0]);
		_tempMap.put(character + "-" + lv + "-" + HerosPropertyConsts.ATK, attr[1]);
		_tempMap.put(character + "-" + lv + "-" + HerosPropertyConsts.SPD, attr[2]);
		_tempMap.put(character + "-" + lv + "-" + HerosPropertyConsts.DEF, attr[3]);
		_tempMap.put(character + "-" + lv + "-" + HerosPropertyConsts.RES, attr[4]);
	}

}
