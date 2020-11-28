package com.spring.springsungjuk.sungjuk;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.springsungjuk.SpringVO;

@Controller
public class SungjukController {
	@Autowired
	private SungjukService sungjukService;
	
	@RequestMapping("/index.me")
	public String indexPage(Model model) throws Exception{
		ArrayList<SpringVO> sungjuk_list = sungjukService.getSungjuklist();
		model.addAttribute("sungjuk_list",sungjuk_list);
		return "index";
	}
	@RequestMapping("/insert.me")
	public String insertPage(Model model) throws Exception{
		return "insert";
	}
	@RequestMapping("/insertProcess.me")
	public String insertSungjuk(SpringVO springVO, HttpServletResponse response) throws Exception{
		int res = sungjukService.insertSungjuk(springVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res != 0) {
			writer.write("<script>alert('성적입력 성공!!'); location.href='./index.me';</script>");
		}
		else {
			writer.write("<script>alert('성적입력 실패!!'); history.back();</script>");
		}
		return null;
	}
	@RequestMapping("/info.me")
	public String infoPage(SpringVO springVO, Model model) throws Exception{
		SpringVO vo = sungjukService.selectSungjuk(springVO.getHakbun());
		model.addAttribute("vo",vo);
		return "info";
	}
	@RequestMapping("/delete.me")
	public String deleteSungjuk(SpringVO springVO) throws Exception{
		sungjukService.deleteSungjuk(springVO);
	
		return "redirect:/index.me";
	}
	@RequestMapping("/update.me")
	public String updatePage(SpringVO springVO, Model model) throws Exception{
		SpringVO vo = sungjukService.selectSungjuk(springVO.getHakbun());
		model.addAttribute("vo",vo); 
		return "update";
	}
	@RequestMapping("/Supdateprocess.me")
	public String updateSungjuk(SpringVO springVO) throws Exception{
		sungjukService.updaqteSungjuk(springVO);
		return "redirect:/index.me";
	}
}
