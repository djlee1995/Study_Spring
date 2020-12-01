package com.spring.mybatis;


import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MybatisController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	//시작 메인화면
	@RequestMapping("/list.do")
	public ModelAndView mainAndView(Model model) {
		//뷰 화면의 main.jsp에 DB로 부터 읽어온 데이터를 보여준다.
		ModelAndView result = new ModelAndView();
		//addObject 뷰에 넘어가는 데이터 
		List<MemberVO> memberList = memberService.getMembers();
		result.addObject("memberList",memberList);
		result.setViewName("list");
		return result;
	}
	
	@RequestMapping("/insert.do")
	public ModelAndView insert(MemberVO member) {
		memberService.insertMember(member);
		/*
		 * HashMap<String, String>map = new HashMap<String, String>(); map.put("id",
		 * member.getId()); map.put("name", member.getName()); map.put("email",
		 * member.getEmail()); map.put("phone", member.getPhone());
		 * memberService.insertMember2(map);
		 */
		ModelAndView result = new ModelAndView();
		List<MemberVO> memberList = memberService.getMembers();
		result.addObject("memberList",memberList);
		result.setViewName("list");
		
		return result;
	}
	@RequestMapping("/updateForm.do")
	public ModelAndView updateForm(MemberVO member) {
		String id = member.getId();
		member = memberService.getMember(id);
		System.out.println("updateForm complete");
		
		ModelAndView result = new ModelAndView();
		result.addObject("member",member);
		result.setViewName("updateForm");
		return result;
	}
	/*
	 * @RequestMapping("/update.do") public ModelAndView update(MemberVO member) {
	 * memberService.updateMember(member); System.out.println("update complete");
	 * 
	 * ModelAndView result = new ModelAndView(); List<MemberVO> memberList =
	 * memberService.getMembers(); result.addObject("memberList",memberList);
	 * result.setViewName("list"); return result; }
	 */
	
	@RequestMapping("/update.do")
	public String update(MemberVO member) {
		memberService.updateMember(member);
		System.out.println("update complete");
		return "redirect:/list.do";
	}
	@RequestMapping("/delete.do")
	public ModelAndView delete(MemberVO member) {
		String id = member.getId();
		memberService.deleteMember(id);
		System.out.println("delete complete");
		
		ModelAndView result = new ModelAndView();
		List<MemberVO> memberList = memberService.getMembers();
		result.addObject("memberList",memberList);
		result.setViewName("list");
		return result;
		
	}
	/*
	 * @RequestMapping("/delete.do") public String delete(MemberVO member) {
	 * memberService.deleteMember(member); System.out.println("delete complete");
	 * return "redirect:/list.do"; }
	 */
}
