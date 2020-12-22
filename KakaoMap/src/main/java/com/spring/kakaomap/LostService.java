package com.spring.kakaomap;

import java.util.HashMap;
import java.util.List;

public interface LostService {
	
	public List<LostVO>place();

	public List<LostVO> lostlist(String loc);
}
