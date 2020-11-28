package com.spring.springsungjuk.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springsungjuk.SpringVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {
	@Autowired(required = false)
	private MemberDAO memberDAO = null;

	@Override
	public int insertMember(SpringVO springVO) {
		int res = memberDAO.insertMember(springVO);

		return res;
	}

	@Override
	public int userCheck(SpringVO springVO) {
		int res = memberDAO.userCheck(springVO);
		return res;
	}

	@Override
	public ArrayList<SpringVO> getMemberlist() {
		ArrayList<SpringVO> member_list = new ArrayList<SpringVO>();
		member_list = memberDAO.getMemberlist();
		return member_list;
	}

	@Override
	public int deleteMember(SpringVO springVO) {
		int res = memberDAO.deleteMember(springVO);
		return res;
	}

	@Override
	public SpringVO selectMember(SpringVO springVO) {

		SpringVO vo = memberDAO.selectMember(springVO);

		return vo;
	}
	
	@Override
	public int updateMember(SpringVO springVO) {
		int res = memberDAO.updateMember(springVO);
		return res;
	}


}
