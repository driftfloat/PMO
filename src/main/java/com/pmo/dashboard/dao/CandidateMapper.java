package com.pmo.dashboard.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.CandidatePush;

public interface CandidateMapper
{
    List<CandidateInfo> queryCandidateList(CandidateInfo candidate);
    int queryCandidateCount(CandidateInfo candidate);
    String queryCandidateResumePath(CandidateInfo candidate);
    List<LinkedHashMap<String,String>> queryExportData(CandidateInfo candidate);
    List<CandidateInfo> queryMyCandidateList(CandidateInfo candidate);
    int queryMyCandidateCount(CandidateInfo candidate);
    List<Map<String,String>> queryCusDeptInfo();
    CandidateInfo queryCandidateForId(String candidateId);
    int updateCandidateInterviewStatus(CandidateInfo candidate);
    int insertCandidatePushData(CandidatePush candidatePush);
    int updateCandidatePushStatus(CandidatePush candidatePush);
}
