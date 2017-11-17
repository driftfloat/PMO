package com.pom.dashboard.service;

import com.pmo.dashboard.entity.User;

/**
 * 用户的Service接口
 * 
 * @author dilu
 * @version 1.0，2017-9-4 22:13:37
 */
public interface UserService {

	 User checkUser(String userName);

	User login(String userName, String password);

	int updatePwd(String userId, String newPwd);

}
