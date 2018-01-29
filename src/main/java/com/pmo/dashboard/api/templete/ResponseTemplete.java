package com.pmo.dashboard.api.templete;

import java.util.List;

public class ResponseTemplete {
	
	
	private String result;//返回码
	private List<String> errorList;//异常集合
	private List<Object> dataList;//数据集合
	
	
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	public List<Object> getDataList() {
		return dataList;
	}
	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}

}
