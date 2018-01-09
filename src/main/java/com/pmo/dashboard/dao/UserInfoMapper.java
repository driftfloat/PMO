package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.UserPageCondition;

public interface UserInfoMapper {

	List<User> queryUserList(UserPageCondition userPageCondition);
	int countUserList(UserPageCondition userPageCondition);
}