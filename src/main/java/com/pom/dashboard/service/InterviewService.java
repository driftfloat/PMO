package com.pom.dashboard.service;

import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HSBCDept;

public interface InterviewService {

	int getCandidateListCount();

	List<CandidateInfo> getCandidateList(CandidateInfo candidate);

	Map<String, List<CandidateInfo>> getInterviewRecordByCandId(String candidateId);

	boolean lockCandidate(String candidateId, String userId);

	String getBillRate(Employee employee);
	
	Employee queryEmployeeById(String employeeId);

	HSBCDept queryHSBCSubDeptById(String hsbcSubDeptId);

}
