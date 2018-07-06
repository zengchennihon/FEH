package org.feh.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.feh.model.functions.ResultModel;
import org.feh.model.vo.HeroAllInfoVo;
import org.feh.service.HeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/feh")
public class HerosController {
	
	@Resource
	private HeroService heroService;
	
	Logger logger = Logger.getLogger(HerosController.class); 

	@RequestMapping(value = "/findAllHeros")
	@ResponseBody
	public ResponseEntity<ResultModel> findAllHeros(){
		List<HeroAllInfoVo> infoVos = heroService.findHerosAllInfoVos();
		return ResponseEntity.ok(ResultModel.result(infoVos));
	}
	
}
