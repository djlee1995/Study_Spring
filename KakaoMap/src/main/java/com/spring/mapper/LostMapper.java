package com.spring.mapper;

import java.util.HashMap;
import java.util.List;

import com.spring.kakaomap.*;;


public interface LostMapper {
	//List<LostVO> getMembers();
	List<LostVO> place();
	List<LostVO> lostlist(String loc);
	

}
