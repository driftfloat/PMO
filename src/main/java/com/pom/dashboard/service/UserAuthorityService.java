package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.UserAuthority;

public interface UserAuthorityService {
	
	
		List<UserAuthority> queryUserAuthority(String userId);
		
		List<UserAuthority> queryMenu(String menuUrl);
		
		List<UserAuthority> queryUserAuthorityMenu(String userId,String menuUrl);
		
		List<UserAuthority> queryMenus();
		
		List<UserAuthority> queryUserAuthorityMenus(String userId);

}
