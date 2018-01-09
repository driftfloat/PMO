package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.UserMapper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.UserService;

/**
 * 用户service接口实现类
 * 
 * @author dilu
 * @version1.0 2017-9-4 22:22:28
 */
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	@Override
	public User checkUser(String userName) {
		// TODO Auto-generated method stub
		return userMapper.checkUser(userName);
	}
	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		return userMapper.login(userName,password);
	}
	@Override
	public int updatePwd(String userId, String newPwd) {
		// TODO Auto-generated method stub
		return userMapper.updatePwd(userId,newPwd);
	}
	@Override
	public List<User> getUserForRM() {
		
		return userMapper.getUserForRM();
	}
	@Override
	public boolean addUser(User user) {
		if(userMapper.addUser(user)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		if(userMapper.updateUser(user)>0){
            return true;
        }
        return false;
	}
	@Override
	public User queryUserById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.queryUserById(userId);
	}

}
