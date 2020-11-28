package com.spring.springsungjuk.sungjuk;

import java.util.ArrayList;

import com.spring.springsungjuk.SpringVO;

public interface SungjukService {
	public ArrayList<SpringVO> getSungjuklist();
	public int insertSungjuk(SpringVO springVO);
	public SpringVO selectSungjuk(String hakbun);
	public int deleteSungjuk(SpringVO springVO);
	public int updaqteSungjuk(SpringVO springVO);
}

