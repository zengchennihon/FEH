package org.feh.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.feh.enums.HeroCharacterEnums;
import org.feh.model.SkillLevel;
import org.feh.model.functions.Result;
import org.feh.model.vo.HeroAllInfoVo;
import org.feh.service.HeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/feh")
public class HerosController {
	
	@Resource
	private HeroService heroService;
	
	private Gson gson = new Gson();
	
	Logger logger = Logger.getLogger(HerosController.class); 

	@RequestMapping(value = "/findAllHeros")
	@ResponseBody
	public ResponseEntity<Result> findAllHeros(){
		List<HeroAllInfoVo> infoVos = heroService.findHerosAllInfoVos();
		return ResponseEntity.ok(Result.result(0, infoVos));
	}
	
	@RequestMapping(value = "/testStr")
	@ResponseBody
	public ResponseEntity<Result> testStr(@RequestBody SkillLevel skillLevel){
		
		System.out.println(gson.toJson(skillLevel));
		return ResponseEntity.ok(Result.result(0, HeroCharacterEnums.INCREASE_BLOOD_ATTACK.getRise() + "--" + HeroCharacterEnums.INCREASE_BLOOD_ATTACK.getDecrease() + "--" + HeroCharacterEnums.INCREASE_BLOOD_ATTACK.getRemark()));
	}

}
