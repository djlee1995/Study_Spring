package com.spring.springsungjuk.member;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.springsungjuk.SpringVO;
import com.spring.springsungjuk.sungjuk.*;

import oracle.net.aso.s;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private SungjukService sungjukservice;

	@RequestMapping("/login.me")
	public String userCheck(SpringVO springVO, HttpServletResponse response,
			HttpServletRequest request,Model model) throws Exception {
		int res = memberService.userCheck(springVO);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("hakbun",springVO.getHakbun());
			writer.write("<script>alert('로그인 성공!!')</script>");
			SpringVO vo= sungjukservice.selectSungjuk(springVO.getHakbun());
			model.addAttribute("vo",vo);
			return "main";
			
		} else {
			writer.write("<script>alert('로그인 실패!!'); location.href='./loginform.me';</script>");

		}
		return null;
	}

	@RequestMapping("/loginform.me")
	public String loginform() throws Exception {
		return "loginform";
	}

	@RequestMapping("/joinform.me")
	public String joinform() throws Exception {
		return "joinform";
	}

	@RequestMapping("/joinprocess.me")
	public String insertMember(SpringVO springVO, HttpServletResponse response) throws Exception {
		int res = memberService.insertMember(springVO);

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res != 0) {
			writer.write("<script>alert('회원가입 성공!!'); location.href='./loginform.me';</script>");
		} else {
			writer.write("<script>alert('회원가입 실패!!'); location.href='./joinform.me';</script>");
		}
		return null;
	}

	@RequestMapping("/updateprocess.me")
	public String updateMember(SpringVO springVO, HttpServletResponse response) throws Exception {
		int res = memberService.updateMember(springVO);

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res != 0) {
			writer.write("<script>alert('회원정보 수정 성공!!'); location.href='./memberlist.me';</script>");
		} else {
			writer.write("<script>alert('회원정보 수정 실패!!'); history.back();</script>");
		}
		return null;
	}

	@RequestMapping("/memberlist.me")
	public String getMemberlist(Model model) throws Exception {
		ArrayList<SpringVO> member_list = memberService.getMemberlist();
		model.addAttribute("member_list", member_list);
		return "member_list";
	}

	@RequestMapping("/memberinfo.me")
	public String selectMember(SpringVO springVO, Model model) throws Exception {
		SpringVO vo = memberService.selectMember(springVO);
		model.addAttribute("springVO", vo);
		return "member_info";
	}

	@RequestMapping("/memberdelete.me")
	public String deleteMember(SpringVO springVO, Model model) throws Exception {
		memberService.deleteMember(springVO);

		return "redirect:/memberlist.me";
	}

	@RequestMapping("/memberupdate.me")
	public String updateMember(SpringVO springVO, Model model) throws Exception {
		SpringVO vo = memberService.selectMember(springVO);
		model.addAttribute("vo", vo);
		return "member_update";
	}
}
