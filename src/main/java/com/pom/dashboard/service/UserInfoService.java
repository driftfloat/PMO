package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.UserPageCondition;

public interface UserInfoService {
	List<User> queryUserList(UserPageCondition userPageCondition);
	int countUserList(UserPageCondition userPageCondition);
}
