package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.UserInfoMapper;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.UserPageCondition;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Resource
	private UserInfoMapper userInfoMapper;

	@Override
	public List<User> queryUserList(UserPageCondition userPageCondition) {

		List<User> list = userInfoMapper.queryUserList(userPageCondition);
		return list;
	}

	@Override
	public int countUserList(UserPageCondition userPageCondition) {
		int pageNumber = userInfoMapper.countUserList(userPageCondition)/Constants.TEN + Constants.ONE;
        return pageNumber;
	}

}
