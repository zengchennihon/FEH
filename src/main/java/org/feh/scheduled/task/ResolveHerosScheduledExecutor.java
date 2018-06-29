package org.feh.scheduled.task;

import java.io.IOException;
import java.util.List;

import org.feh.model.Hero;
import org.feh.model.functions.HeroSource;
import org.feh.model.functions.HeroSourceAttr;
import org.feh.utils.ResolveHerosUtils;
import org.feh.utils.UrlUtils;

public class ResolveHerosScheduledExecutor {
	
	public void run() {
		List<HeroSource> sources = ResolveHerosUtils.getHeroSourcesByFile();
		System.out.println(sources.size());
		sources.forEach(s -> {
			String aid = s.getAid();
			System.out.println(aid);
			String name_jp = s.getName();
			HeroSourceAttr st3 = s.getSt3();
			HeroSourceAttr st3_s = s.getSt3_s();
			HeroSourceAttr st4 = s.getSt4();
			HeroSourceAttr st4_s = s.getSt4_s();
			HeroSourceAttr st5 = s.getSt5();
			HeroSourceAttr st5_s = s.getSt5_s();
			String reco = s.getReco();
			String remark = s.getRemark();
			
			//获取hero头像
			String temp = UrlUtils.sendGet(ResolveHerosUtils.heroHead + aid + ".png", null, null);
			try {
				Hero hero = new Hero();
				hero.setHeadPortrait(temp.getBytes("UTF-8"));
				hero.setGender((byte) 1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}

}
