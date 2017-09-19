package com.pmo.dashboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pmo.dashboard.entity.UserAuthority;

public interface UserAuthorityMapper {
	
	List<UserAuthority> queryUserAuthority(@Param("userId") String userId);
	
	List<UserAuthority> queryUserMenu(@Param("menuUrl") String menuUrl);
	
	List<UserAuthority> queryUserAuthorityMenu(@Param("userId") String userId,@Param("menuUrl") String menuUrl);

	List<UserAuthority> queryMenus();
	
	List<UserAuthority> queryUserAuthorityMenus(String userId);
}
