package com.spring.springsungjuk.member;

import java.util.ArrayList;

import com.spring.springsungjuk.SpringVO;

public interface MemberService {
	public int insertMember(SpringVO springVO);
	public int userCheck(SpringVO springVO);
	public  ArrayList<SpringVO> getMemberlist();
	public SpringVO selectMember(SpringVO springVO);
	public int deleteMember(SpringVO springVO);
	public int updateMember(SpringVO springVO);
}
