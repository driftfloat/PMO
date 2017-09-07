package com.pmo.dashboard.service.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.pmo.dashboard.dao.CandidateMapper;
import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.CandidatePush;
import com.pom.dashboard.service.CandidateService;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Resource
	private CandidateMapper candidateMapper;

	@Override
	public List<CandidateInfo> queryCandidateList(CandidateInfo candidate) {
		return transferData(candidateMapper.queryCandidateList(candidate));
	}

	@Override
	public int queryCandidateCount(CandidateInfo candidate) {
		return candidateMapper.queryCandidateCount(candidate);
	}

	@Override
	public List<LinkedHashMap<String, String>> queryExportData(CandidateInfo candidate) {
		return transferListData(candidateMapper.queryExportData(candidate));
	}

	private List<LinkedHashMap<String, String>> transferListData(List<LinkedHashMap<String, String>> candidateList) {
		try {
			if (null != candidateList && candidateList.size() > 0) {
				for (LinkedHashMap<String, String> map : candidateList) {
					map.put("CANDIDATE_SEX", "0".equals(map.get("CANDIDATE_SEX")) ? "男" : "女");
					map.put("ENGLISH_LEVEL", "0".equals(map.get("ENGLISH_LEVEL")) ? "非工作语言" : "工作语言");
					map.put("MAJOR_STATUS", "0".equals(map.get("MAJOR_STATUS")) ? "是" : "否");
					String candidateStatus = map.get("CANDIDATE_STATUS");
					if ("0".equals(candidateStatus)) {
						candidateStatus = "招聘中";
					} else if ("1".equals(candidateStatus)) {
						candidateStatus = "offer中";
					} else if ("2".equals(candidateStatus)) {
						candidateStatus = "已入职";
					} else if ("3".equals(candidateStatus)) {
						candidateStatus = "闲置中";
					} else if ("4".equals(candidateStatus)) {
						candidateStatus = "暂不关注";
					} else if ("5".equals(candidateStatus)) {
						candidateStatus = "黑名单";
					} else if ("6".equals(candidateStatus)) {
						candidateStatus = "入职他司";
					}
					map.put("CANDIDATE_STATUS", candidateStatus);

					String education = map.get("EDUCATION");
					if ("0".equals(education)) {
						education = "博士";
					} else if ("1".equals(education)) {
						education = "研究生";
					} else if ("2".equals(education)) {
						education = "本科";
					} else if ("3".equals(education)) {
						education = "大专";
					} else if ("4".equals(education)) {
						education = "高中";
					}
					map.put("EDUCATION", education);
					
					String interviewStatus = map.get("INTERVIEW_STATUS");
					if ("0".equals(interviewStatus)){
						interviewStatus = "未推送";
					}else if ("1".equals(interviewStatus)){
						interviewStatus = "已推送";
					}else if ("2".equals(interviewStatus)){
						interviewStatus = "面试中";
					}else if ("3".equals(interviewStatus)){
						interviewStatus = "面试完成";
					}else if ("4".equals(interviewStatus)){
						interviewStatus = "已退回";
					}
					map.put("INTERVIEW_STATUS", interviewStatus);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateList;
	}

	private List<CandidateInfo> transferData(List<CandidateInfo> candidateList) {
		try {
			if (null != candidateList && candidateList.size() > 0) {
				for (CandidateInfo candidateInfo : candidateList) {
					candidateInfo.setCandidateSex("0".equals(candidateInfo.getCandidateSex()) ? "男" : "女");
					candidateInfo.setEnglishLevel("0".equals(candidateInfo.getEnglishLevel()) ? "非工作语言" : "工作语言");
					candidateInfo.setMajorStatus("0".equals(candidateInfo.getMajorStatus()) ? "是" : "否");
					String candidateStatus = candidateInfo.getCandidateStatus();
					if ("0".equals(candidateStatus)) {
						candidateStatus = "招聘中";
					} else if ("1".equals(candidateStatus)) {
						candidateStatus = "offer中";
					} else if ("2".equals(candidateStatus)) {
						candidateStatus = "已入职";
					} else if ("3".equals(candidateStatus)) {
						candidateStatus = "闲置中";
					} else if ("4".equals(candidateStatus)) {
						candidateStatus = "暂不关注";
					} else if ("5".equals(candidateStatus)) {
						candidateStatus = "黑名单";
					} else if ("6".equals(candidateStatus)) {
						candidateStatus = "入职他司";
					}
					candidateInfo.setCandidateStatus(candidateStatus);

					String education = candidateInfo.getEducation();
					if ("0".equals(education)) {
						education = "博士";
					} else if ("1".equals(education)) {
						education = "研究生";
					} else if ("2".equals(education)) {
						education = "本科";
					} else if ("3".equals(education)) {
						education = "大专";
					} else if ("4".equals(education)) {
						education = "高中";
					}
					candidateInfo.setEducation(education);
					
					String interviewStatus = candidateInfo.getInterviewStatus();
					if ("0".equals(interviewStatus)){
						interviewStatus = "未推送";
					}else if ("1".equals(interviewStatus)){
						interviewStatus = "已推送";
					}else if ("2".equals(interviewStatus)){
						interviewStatus = "面试中";
					}else if ("3".equals(interviewStatus)){
						interviewStatus = "面试完成";
					}else if ("4".equals(interviewStatus)){
						interviewStatus = "已退回";
					}
					candidateInfo.setInterviewStatus(interviewStatus);
					
					@SuppressWarnings("rawtypes")
					Class cls = candidateInfo.getClass();  
					Field[] fields = cls.getDeclaredFields(); 
					for ( int i = 0; i < fields.length; i++ )  
					{  
						Field f = fields[i];  
				        f.setAccessible(true);
						if(f.get(candidateInfo) == null)
						{
							 f.set(candidateInfo, "");
						}
					}  
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateList;
	}
	
	@Override
	public void transferExportData(List<LinkedHashMap<String, String>> candidateDatalist, List<String> conditionList,
			File file) {
		try {

			// 以fileName为文件名来创建一个Workbook
			WritableWorkbook wwb = Workbook.createWorkbook(file);

			// 创建sheet页工作表
			WritableSheet ws = wwb.createSheet("Candidate List", 0);
			// 设置列宽
			ws.setColumnView(0, 10);
			for (int i = 0; i < conditionList.size(); i++) {
				ws.setColumnView(i + 1, 20); // 第1列宽
			}
			// 设置行高
			ws.setRowView(0, 500);
			for (int i = 0; i < candidateDatalist.size(); i++) {
				ws.setRowView(i + 1, 300);
			}
			ws.getSettings().setVerticalFreeze(1);
			ws.getSettings().setHorizontalFreeze(2);
			// 标题样式
			WritableFont headFont = new WritableFont(WritableFont.TIMES, 14, WritableFont.BOLD);
			WritableCellFormat headcell = new WritableCellFormat(headFont);
			headcell.setAlignment(Alignment.CENTRE);//  单元格内容水平居中  
			headcell.setBackground(Colour.GREEN);// 背景色 
			headcell.setVerticalAlignment(VerticalAlignment.CENTRE);//  单元格内容垂直居中
			headcell.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK); //  边框
			headcell.setWrap(true);// 是否换行
			// 内容样式
			WritableFont contentFont = new WritableFont(WritableFont.TIMES, 12);
			WritableCellFormat contentcell = new WritableCellFormat(contentFont);
			contentcell.setAlignment(Alignment.LEFT);//  单元格内容水平居左  
			contentcell.setVerticalAlignment(VerticalAlignment.CENTRE);//  单元格内容垂直居中
			contentcell.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK); //  边框
			contentcell.setWrap(false);// 是否换行
			// 要插入到的Excel表格的行号，默认从0开始
			Label labelSL = new Label(0, 0, "SL#", headcell);
			ws.addCell(labelSL);

			for (int k = 0; k < conditionList.size(); k++) {
				Label label = new Label(k + 1, 0, conditionList.get(k), headcell);
				ws.addCell(label);
			}

			for (int i = 1; i - 1 < candidateDatalist.size(); i++) {
				LinkedHashMap<String, String> map = candidateDatalist.get(i - 1);
				int j = 0;
				Label labelSL_i = new Label(j, i, i + "", contentcell);
				ws.addCell(labelSL_i);
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (entry.getValue() == null) {
						Label label = new Label(++j, i, "", contentcell);
						ws.addCell(label);
					} else {
						Label label = new Label(++j, i, entry.getValue(), contentcell);
						ws.addCell(label);
					}
				}
			}

			// 写进文档
			wwb.write();
			// 关闭Excel工作簿对象
			wwb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String queryCandidateResumePath(CandidateInfo candidate) {
		return candidateMapper.queryCandidateResumePath(candidate);
	}

	@Override
	public List<CandidateInfo> queryMyCandidateList(CandidateInfo candidate) 
	{
		return transferData(candidateMapper.queryMyCandidateList(candidate));
	}

	@Override
	public int queryMyCandidateCount(CandidateInfo candidate) 
	{
		return candidateMapper.queryMyCandidateCount(candidate);
	}

	@Override
	public List<Map<String,String>> queryCusDeptInfo() 
	{
		return candidateMapper.queryCusDeptInfo();
	}

	@Override
	public CandidateInfo queryCandidateForId(String candidateId) 
	{
		return candidateMapper.queryCandidateForId(candidateId);
	}

	@Override
	public boolean updateCandidateInterviewStatus(CandidateInfo candidate) 
	{
		if(candidateMapper.updateCandidateInterviewStatus(candidate) > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean insertCandidatePushData(CandidatePush candidatePush) 
	{
		if(candidateMapper.insertCandidatePushData(candidatePush) > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCandidatePushStatus(CandidatePush candidatePush) {
		if(candidateMapper.updateCandidatePushStatus(candidatePush) > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCandidateStatus(CandidateInfo candidate) 
	{
		return candidateMapper.updateCandidateStatus(candidate);
	}
	
	public List<CandidateInfo> queryinterviewFeedBack(CandidateInfo candidate) {
		return transferData(candidateMapper.queryinterviewFeedBack(candidate));
	}

	@Override
	public int queryinterviewFeedBackCount(CandidateInfo candidate) {
		return candidateMapper.queryinterviewFeedBackCount(candidate);
	}

	@Override
	public boolean updateInterviewFeedBack(CandidateInfo candidate) {
		int resCount = candidateMapper.updateInterviewFeedBack(candidate);
		boolean flag = resCount > 0 ? true : false;
		return flag;
	}

}
