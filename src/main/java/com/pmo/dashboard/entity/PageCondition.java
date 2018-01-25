package com.pmo.dashboard.entity;

public class PageCondition
{
    private String currentPage;
    private String pageCount;

    private Integer currPage;
    private Integer totalPage;
    private Integer pageSize = 10;

    private String pageDataCount;
    private String dataCount;
    
    private Integer pageRecNum;
    
    
    
    //search条件
    private String name;
    private String tel;
    
    
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getPageRecNum() {
		return pageRecNum;
	}
	public void setPageRecNum(Integer pageRecNum) {
		this.pageRecNum = pageRecNum;
	}
	public String getCurrentPage()
    {
        return currentPage;
    }
    public void setCurrentPage(String currentPage)
    {
        this.currentPage = currentPage;
    }
    public String getPageCount()
    {
        return pageCount;
    }
    public void setPageCount(String pageCount)
    {
        this.pageCount = pageCount;
    }
    public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public PageCondition()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public PageCondition(String currentPage, String pageCount)
    {
        super();
        this.currentPage = currentPage;
        this.pageCount = pageCount;
    }
	public String getPageDataCount() {
		return pageDataCount;
	}
	public void setPageDataCount(String pageDataCount) {
		this.pageDataCount = pageDataCount;
	}
	public String getDataCount() {
		return dataCount;
	}
	public void setDataCount(String dataCount) {
		this.dataCount = dataCount;
	}
    
}
