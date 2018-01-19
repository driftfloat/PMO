package com.pom.dashboard.service;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.CandidateInterview;
import com.pmo.dashboard.entity.CandidatePush;

public interface CandidateService
{
    List<CandidateInfo> queryCandidateList(CandidateInfo candidate);

	int queryCandidateCount(CandidateInfo candidate);
	
	String queryCandidateResumePath(CandidateInfo candidate);
	
    List<LinkedHashMap<String, String>> queryExportData(CandidateInfo candidate);
    
    void transferExportData( List<LinkedHashMap<String,String>> candidateDatalist,List<String> conditionList,File file);
    
    List<CandidateInfo> queryMyCandidateList(CandidateInfo candidate);

	int queryMyCandidateCount(CandidateInfo candidate);
	
	List<Map<String,String>> queryCusDeptInfo();  
	
	boolean updateCandidateStatus(CandidateInfo candidate);

	List<CandidateInfo> queryinterviewFeedBack(CandidateInfo candidate);
	
	List<CandidateInfo> queryinterviewAllFeedBack(CandidateInfo candidate);

	int queryinterviewFeedBackCount(CandidateInfo candidate);
	
	int queryinterviewAllFeedBackCount(CandidateInfo candidate);

	boolean updateInterviewFeedBack(CandidateInfo candidate);
	
	String pushCandidateOk(CandidatePush candidatePush,HttpServletRequest request);
	
	String backCandidateToDept(HttpServletRequest request);
	
	int queryMyWaitEntryCandidateCount(CandidateInfo candidate);
	
	List<CandidateInfo> queryMyWaitEntryCandidateList(CandidateInfo candidate);
	
	boolean updateCandidateInfo(CandidateInfo candidateinfo);
	
	boolean entryMyWaitCandidateOk(CandidateInfo candidateinfo);
	
	boolean delayMyWaitCandidateOk(CandidateInfo candidateinfo);
	
	boolean abortMyWaitCandidateOk(CandidateInfo candidateinfo);
	
	CandidateInfo queryCandidateForId(String candidateId);
	
	boolean updateCandidateInterviewStatus(CandidateInfo candidate);
	
	boolean updateOnboardCandidate(String candidateId);
	static List<CandidateInfo> queryCandidateNameById(String candidateId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	List<CandidateInfo> queryBlackList(CandidateInfo candidate);
	
	int queryBlackListCount(CandidateInfo candidate);
	
	CandidateInterview queryCandidateInterviewById(String interviewId);
	
	boolean updateCandidateInterviewMark(CandidateInterview candidateInterview);

}
