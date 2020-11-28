package com.spring.springsungjuk.sungjuk;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springsungjuk.*;

@Service("SungjukService")
public class SungjukServiceImpl implements SungjukService {
	@Autowired(required = false)
	private SungjukDAO sungjukDAO = null;
	@Override
	public ArrayList<SpringVO> getSungjuklist() {
		ArrayList<SpringVO> sungjuk_list = new ArrayList<SpringVO>();
		sungjuk_list = sungjukDAO.getSungjuklist();
		
		return sungjuk_list;
	}
	@Override
	public int insertSungjuk(SpringVO springVO) {
		int res = sungjukDAO.insertSungjuk(springVO);
		
		return res;
	}
	@Override
	public SpringVO selectSungjuk(String hakbun) {
		SpringVO vo = sungjukDAO.selectSungjuk(hakbun);
		
		return vo;
	}
	@Override
	public int deleteSungjuk(SpringVO springVO) {
		int res = sungjukDAO.deleteSungjuk(springVO);
		return res;
	}
	@Override
	public int updaqteSungjuk(SpringVO springVO) {
		int res = sungjukDAO.updateSungjuk(springVO);
		return res;
	}

}
