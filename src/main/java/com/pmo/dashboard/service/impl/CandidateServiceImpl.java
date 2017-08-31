package com.pmo.dashboard.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.pmo.dashboard.dao.CandidateMapper;
import com.pmo.dashboard.entity.CandidateInfo;
import com.pom.dashboard.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService
{

    @Resource
    private CandidateMapper candidateMapper;
    
	@Override
	public List<CandidateInfo> queryCandidateList(CandidateInfo candidate) 
	{
		return transferData(candidateMapper.queryCandidateList(candidate));
	}

	@Override
	public int queryCandidateCount(CandidateInfo candidate) 
	{
		return candidateMapper.queryCandidateCount(candidate);
	}

	@Override
	public List<LinkedHashMap<String,String>> queryExportData(CandidateInfo candidate) 
	{
		return transferListData(candidateMapper.queryExportData(candidate));
	}
	private List<LinkedHashMap<String,String>> transferListData(List<LinkedHashMap<String,String>> candidateList)
	{
		try {
			if(null != candidateList && candidateList.size()>0)
			{
				for (LinkedHashMap<String,String> map : candidateList) 
				{
					map.put("CANDIDATE_SEX", "0".equals(map.get("CANDIDATE_SEX"))?"男":"女");
					map.put("ENGLISH_LEVEL", "0".equals(map.get("ENGLISH_LEVEL"))?"简单使用":"流利沟通");
					map.put("MAJOR_STATUS", "0".equals(map.get("MAJOR_STATUS"))?"是":"否");
					String candidateStatus = map.get("CANDIDATE_STATUS");
					if ("0".equals(candidateStatus)){
						candidateStatus = "招聘中";
					}else if ("1".equals(candidateStatus)){
						candidateStatus = "offer中";
					}else if ("2".equals(candidateStatus)){
						candidateStatus = "已入职";
					}else if ("3".equals(candidateStatus)){
						candidateStatus = "闲置中";
					}else if ("4".equals(candidateStatus)){
						candidateStatus = "暂不关注";
					}
					map.put("CANDIDATE_STATUS", candidateStatus);
					
					String education = map.get("EDUCATION");
					if ("0".equals(education)){
						education = "博士";
					}else if ("1".equals(education)){
						education = "研究生";
					}else if ("2".equals(education)){
						education = "本科";
					}else if ("3".equals(education)){
						education = "大专";
					}else if ("4".equals(education)){
						education = "高中";
					}
					map.put("EDUCATION", education);

					String source = map.get("SOURCE");
					if ("0".equals(source)){
						source = "51job";
					}else if ("1".equals(source)){
						source = "智联招聘";
					}else if ("2".equals(source)){
						source = "Boss直聘";
					}else if ("3".equals(source)){
						source = "中华英才网";
					}else if ("4".equals(source)){
						source = "其他";
					}
					map.put("SOURCE", source);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateList;
	}
	private List<CandidateInfo> transferData(List<CandidateInfo> candidateList)
	{
		try {
			if(null != candidateList && candidateList.size()>0)
			{
				for (CandidateInfo candidateInfo : candidateList) 
				{
					candidateInfo.setCandidateSex("0".equals(candidateInfo.getCandidateSex())?"男":"女");
					candidateInfo.setEnglishLevel("0".equals(candidateInfo.getEnglishLevel())?"简单使用":"流利沟通");
					candidateInfo.setMajorStatus("0".equals(candidateInfo.getMajorStatus())?"是":"否");
					String candidateStatus = candidateInfo.getCandidateStatus();
					if ("0".equals(candidateStatus)){
						candidateStatus = "招聘中";
					}else if ("1".equals(candidateStatus)){
						candidateStatus = "offer中";
					}else if ("2".equals(candidateStatus)){
						candidateStatus = "已入职";
					}else if ("3".equals(candidateStatus)){
						candidateStatus = "闲置中";
					}else if ("4".equals(candidateStatus)){
						candidateStatus = "暂不关注";
					}
					candidateInfo.setCandidateStatus(candidateStatus);
					
					String education = candidateInfo.getEducation();
					if ("0".equals(education)){
						education = "博士";
					}else if ("1".equals(education)){
						education = "研究生";
					}else if ("2".equals(education)){
						education = "本科";
					}else if ("3".equals(education)){
						education = "大专";
					}else if ("4".equals(education)){
						education = "高中";
					}
					candidateInfo.setEducation(education);
					
					String source = candidateInfo.getSource();
					if ("0".equals(source)){
						source = "51job";
					}else if ("1".equals(source)){
						source = "智联招聘";
					}else if ("2".equals(source)){
						source = "Boss直聘";
					}else if ("3".equals(source)){
						source = "中华英才网";
					}else if ("4".equals(source)){
						source = "其他";
					}
					candidateInfo.setSource(source);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateList;
	}

}
