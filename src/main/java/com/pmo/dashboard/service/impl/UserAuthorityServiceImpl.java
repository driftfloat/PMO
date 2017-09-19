package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.UserAuthorityMapper;
import com.pmo.dashboard.entity.UserAuthority;
import com.pom.dashboard.service.UserAuthorityService;
@Service
public class UserAuthorityServiceImpl implements UserAuthorityService {

	
	@Resource
	private UserAuthorityMapper userAuthorityMapper;
	
	@Override
	public List<UserAuthority> queryUserAuthority(String userId) {
		// TODO Auto-generated method stub
		return userAuthorityMapper.queryUserAuthority(userId);
	}

	@Override
	public List<UserAuthority> queryMenu(String menuUrl){
		// TODO Auto-generated method stub
		return userAuthorityMapper.queryUserMenu(menuUrl);
	}
	
	@Override
	public List<UserAuthority> queryUserAuthorityMenu(String userId,String menuUrl) {
		// TODO Auto-generated method stub
		return userAuthorityMapper.queryUserAuthorityMenu(userId,menuUrl);
	}
	
	@Override
	public List<UserAuthority> queryMenus(){
		// TODO Auto-generated method stub
		return userAuthorityMapper.queryMenus();
	}
	
	@Override
	public List<UserAuthority> queryUserAuthorityMenus(String userId) {
		// TODO Auto-generated method stub
		return userAuthorityMapper.queryUserAuthorityMenus(userId);
	}
}
