package com.pmo.dashboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.pmo.dashboard.dao.InterviewerMapper;
import com.pmo.dashboard.entity.Interviewer;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.InterviewerService;

@Service
@Transactional
public class InterviewerServiceImpl implements InterviewerService
{

    @Resource
    private InterviewerMapper interviewerMapper;
    
	
	


	@Override
	public List<Interviewer> queryAllInterviewer(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Interviewer> list = interviewerMapper.queryAllInterviewer(map);
		return list;
	}

    //查询根据条件的面试官信息
	@Override
	public List<Interviewer> queryInterviewerList(Interviewer interviewer)
	{
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("interviewer", interviewer);
		//计算当前页起始号
		int num = (interviewer.getCurrPage() - 1)*interviewer.getPageSize();
		map.put("num", num);
		
		List<Interviewer> list = interviewerMapper.queryInterviewerList(map);
		//设置总页数
		int queryInterviewerCount = interviewerMapper.queryInterviewerCount(map);
		interviewer.setTotalPage((int) Math.ceil(queryInterviewerCount*1.0 / interviewer.getPageSize()));
		return list;
	}

	@Override
	public boolean interviewerRenDing(Interviewer interviewer) 
	{
		
		boolean flag1 = interviewerMapper.interviewerRenDing(interviewer);
		
		return flag1;
	
		/*if(flag1)
		{
			return interviewerMapper.insertInterviewerToUser(interviewer);
		}	
		return false;*/
	}

	@Override
	public boolean insertInterviewerToUser(Interviewer interviewer) {
		// TODO Auto-generated method stub
		if(interviewerMapper.insertInterviewerToUser(interviewer)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean update(Interviewer interviewer) {
		// 调用dao层
		if(interviewerMapper.update(interviewer)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Interviewer selectInterviewer(String employeeId) {
		Interviewer interviewer = interviewerMapper.select(employeeId);
		return interviewer;
	}

	@Override
	public User selectUser(String employeeId) {
		User user =interviewerMapper.selectUser(employeeId);
		return user;
	}
    
     

	}

