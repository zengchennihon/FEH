package org.feh.action;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.feh.model.Hero;
import org.feh.service.HeroService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping(value = "/img")
public class ImageSteamOutputAction {
	
	@Resource
	private HeroService heroService;

	@RequestMapping(value = "/heroHead/{aid}")
	@ResponseBody
	public void getHeroHeadByAid(@PathVariable("aid") String aid, HttpServletResponse response) {
		OutputStream out = null;
		try {
			Hero hero = heroService.findByAid(aid);
			if(hero != null) {
				byte[] headImg = hero.getHeadPortrait();
//				response.setContentType("image/png;charset=utf-8");
				out = response.getOutputStream();
				out.write(headImg);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
