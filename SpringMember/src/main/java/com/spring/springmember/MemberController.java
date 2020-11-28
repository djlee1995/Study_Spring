package com.spring.springmember;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/login.me")
	public String userCheck(MemberVO memberVO, HttpSession session, HttpServletResponse response) throws Exception{
		int res = memberService.userCheck(memberVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res==1) {
			session.setAttribute("id", memberVO.getId());
			writer.write("<script>alert('�α��� ����!!'); location.href='./main.me';</script>");
			//return "redirect:/main.me";
		}
		else {
			writer.write("<script>alert('�α��� ����!!'); location.href='./loginform.me';</script>");
			//return "redirect:/loginform.me";
		}
		return null;
	}
	@RequestMapping("/main.me")
	public String mainPage() throws Exception{
		return "main";
	}
	@RequestMapping("/loginform.me")
	public String loginform() throws Exception{
		return "loginform";
	}
	@RequestMapping("/joinform.me")
	public String joinform() throws Exception{
		return "joinform";
	}
	@RequestMapping("/joinprocess.me")
	public String insertMember(MemberVO memberVO, HttpServletResponse response) throws Exception {
		int res = memberService.insertMember(memberVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res != 0) {
			writer.write("<script>alert('ȸ������ ����!!'); location.href='./loginform.me';</script>");
		}
		else {
			writer.write("<script>alert('ȸ������ ����!!'); location.href='./joinform.me';</script>");
		}
		return null;
	}
	
	@RequestMapping("/updateprocess.me")
	public String updateMember(MemberVO memberVO, HttpServletResponse response) throws Exception {
		int res = memberService.updateMember(memberVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res != 0) {
			writer.write("<script>alert('ȸ������ ���� ����!!'); location.href='./memberlist.me';</script>");
		}
		else {
			writer.write("<script>alert('ȸ������ ���� ����!!'); history.back();</script>");
		}
		return null;
	}
	@RequestMapping("/memberlist.me")
	public String getMemberlist(Model model) throws Exception {
		ArrayList<MemberVO> member_list = memberService.getMemberlist();
		model.addAttribute("member_list",member_list);
		return "member_list";
	}
	@RequestMapping("/memberinfo.me")
	public String selectMember(MemberVO memberVO, Model model) throws Exception {
		MemberVO vo = memberService.selectMember(memberVO);
		model.addAttribute("memberVO",vo);
		return "member_info";
	}
	@RequestMapping("/memberdelete.me")
	public String deleteMember(MemberVO memberVO, Model model) throws Exception {
		memberService.deleteMember(memberVO);
		
		return "redirect:/memberlist.me";
	}
	@RequestMapping("/memberupdate.me")
	public String updateMember(MemberVO memberVO, Model model) throws Exception {
		MemberVO vo=memberService.selectMember(memberVO);
		model.addAttribute("vo",vo);
		return "member_update";
	}
}