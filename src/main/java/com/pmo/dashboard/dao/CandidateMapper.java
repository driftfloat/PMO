package com.pmo.dashboard.dao;

import java.util.List;
import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.EmployeePageCondition;

public interface CandidateMapper
{
    List<CandidateInfo> queryCandidateList(CandidateInfo candidate);
    int queryCandidateCount(CandidateInfo candidate);
}
