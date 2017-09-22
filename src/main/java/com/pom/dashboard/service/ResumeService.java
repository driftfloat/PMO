package com.pom.dashboard.service;

import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.Resume;

/**
 * 候选人Service接口
 * 
 * @author dilu
 * @version 1.0 2017-8-28 10:36:44
 */
public interface ResumeService {

	/**
	 * 录入候选人的方法
	 * 
	 * @param candidate
	 */
	void add(Resume resume);
	
	boolean upd(Resume resume);

	Resume searchTel(String tel);
	
	public List<Resume> queryResumeInfoById(String canID);

}
