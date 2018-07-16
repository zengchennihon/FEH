package org.feh.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.feh.enums.ResultCodeEnums;
import org.feh.model.functions.ResultModel;
import org.feh.model.vo.HeroAllInfoVo;
import org.feh.model.vo.HeroBaseInfoVo;
import org.feh.service.HeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping(value = "/feh")
public class HerosController {
	
	@Resource
	private HeroService heroService;
	
	Logger logger = Logger.getLogger(HerosController.class); 

	@RequestMapping(value = "/findHeros")
	@ResponseBody
	public ResponseEntity<ResultModel> findHeros(Integer startPage, Integer count){
		if(startPage == null || count == null) {
			return ResponseEntity.ok(ResultModel.result(ResultCodeEnums.ERROR_PARAMETER_NULL.getCode(), null));
		}
		Page<HeroBaseInfoVo> page = PageHelper.startPage(startPage, count);
		List<HeroBaseInfoVo> infoVos = heroService.findHeros();
		return ResponseEntity.ok(ResultModel.result(infoVos, page.getPageNum(), page.getTotal()));
	}
	
	@RequestMapping("/findHeroDetails")
	@ResponseBody
	public ResponseEntity<ResultModel> findHeroDetails(String aid){
		if(aid == null) {
			return ResponseEntity.ok(ResultModel.result(ResultCodeEnums.ERROR_PARAMETER_NULL.getCode(), null));
		}
		HeroAllInfoVo infoVo = heroService.findAllInfoVoByAid(aid);
		return ResponseEntity.ok(ResultModel.result(infoVo));
	}
	
}
