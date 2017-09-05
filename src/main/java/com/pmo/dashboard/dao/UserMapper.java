package com.pmo.dashboard.dao;

import org.apache.ibatis.annotations.Param;

import com.pmo.dashboard.entity.User;

/**
 * 用户dao接口
 * 
 * @author dilu
 * @version 1.0， 2017-9-4 22:24:51
 */
public interface UserMapper {

	User checkUser(String userName);

	User login(@Param("userName") String userName,@Param("password") String password);

}
