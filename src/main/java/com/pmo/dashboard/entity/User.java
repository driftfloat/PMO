package com.pmo.dashboard.entity;

/**
 * 用户实体类
 * 
 * @author dilu
 * @version 1.0，2017-9-4 18:23:12
 */
public class User {

	//用户编号
	private String userId;
	
	//用户名
	private String userName;
	
	//昵称
	private String nickname;
	
	//密码
	private String password;
	
	//用户类型
	private String user_type;
	
	//所属部门
	private CSDept csDept;
	
	

	public CSDept getCsDept() {
		return csDept;
	}

	public void setCsDept(CSDept csDept) {
		this.csDept = csDept;
	}

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	
}
