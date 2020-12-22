package com.spring.kakaomap;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
	@Autowired
	private LostService lostService;
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/place.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<LostVO> place() {
		System.out.println("AAAAAAAAAAA");
		List<LostVO> place = lostService.place();
		System.out.println("place="+place.size());
		return place;
	}
	@RequestMapping(value = "/lostlist.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<LostVO> lostlist(@RequestParam(value = "loc" , required = false) String loc) {
		System.out.println("BBBBBBBBBB");
		List<LostVO> lostlist = lostService.lostlist(loc);
		System.out.println("lostlist="+lostlist);
		return lostlist;
	}
	
}
