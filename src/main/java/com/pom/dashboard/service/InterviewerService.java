package com.pom.dashboard.service;

import java.util.List;
import java.util.Map;
import com.pmo.dashboard.entity.Interviewer;

public interface InterviewerService
{
	public List<Interviewer> queryInterviewerList(Interviewer interviewer); 
	public List<Interviewer> queryAllInterviewer(Map<String, Object> map);
	
	public boolean interviewerRenDing(Interviewer interviewer);
	//插入到用户表中
	public boolean insertInterviewerToUser(Interviewer interviewer);
	public boolean update(Interviewer interviewer);
	public Interviewer selectInterviewer(String employeeId);
}
